/*******************************************************************************
 * An importer for the vampire editor handling vmpcs format.
 * Copyright (C) 2013 Matthias Reimchen
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *    
 * For further information see https://code.google.com/p/vampire-editor/ or write a
 * mail to development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.importer.vmpcs.application;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import vampire.editor.fileformat.vmpcs.domain.Constructors;
import vampire.editor.fileformat.vmpcs.domain.ModelToViewMap;
import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;
import vampire.editor.importer.vmpcs.domain.MergedObjects;
import vampire.editor.importer.vmpcs.persistency.FileUtils;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.ModelConstructors;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapper;
import vampire.editor.plugin.api.domain.sheet.Value;
import vampire.editor.plugin.api.domain.sheet.VampireDocument;
import vampire.editor.plugin.api.importer.DocumentImportException;
import vampire.editor.plugin.api.importer.SheetImporter;

public class VMPCSImporter implements SheetImporter{
	
	private static int importedDocuments = 0;
	
	private static final Path tmpPath = Paths.get(System.getProperty("java.io.tmpdir"), "vampireeditor","importer","vmpcs");
	
	private final ResourcesHolderAPI resourcesHolder;
	
	private final ModelImporter modelImporter = new ModelImporter();
	
	private final MapidResolver mapidResolver = new MapidResolver();
	
	private final ObjectsLoader objectsLoader;
	
	public VMPCSImporter(ResourcesHolderAPI resourcesHolder) {
		super();
		this.resourcesHolder = resourcesHolder;
		this.objectsLoader = new ObjectsLoader(resourcesHolder);
	}

	public VampireDocument loadDocument(Path path) throws DocumentImportException{
		try {
			return loadDocument(path, false);
		} catch (IOException e) {
			throw new VMPCSImportException(e);
		}
	}
	
	public VampireDocument loadDocument(Path path, boolean compressed) throws IOException{
		Path zipPath = path;
		FileSystem fileSystem = FileSystems.newFileSystem(zipPath,null);
		Path pluginDirectory = fileSystem.getPath("/plugins");
		if (compressed){
			path = uncompress(path);
		}
		else{
			path = fileSystem.getPath("/");
		}
		MergedObjects mergedObjects = objectsLoader.loadObjects(path);
		ProtoSheet protoSheet = modelImporter.loadSheet(path.resolve("sheet.json"));
		Map<Integer, Object> protoMapIdMap = mapidResolver.generateMapIdMap(protoSheet);
		ModelToViewMap modelToViewMap = modelImporter.buildProtoIdMap(path);
		Objects<Value> realValues = new Objects<>(path, Value.class, resourcesHolder, null);
		SheetAndMapIdHolder holder = modelImporter.buildSheet(protoSheet, protoMapIdMap, realValues);
		Map<Integer, Object> mapIdToRealValues = holder.getMapIdToRealModelMap();
		ModelToViewModelMapper modelToViewModelMapper = Constructors.constructors.createModelToViewModelMapper();
		Set<Integer> keys = mapIdToRealValues.keySet();
		for (Integer mapid : keys){
			Object object = mapIdToRealValues.get(mapid);
			Integer id = modelToViewMap.getViewAttIdOf(mapid);
			Object viewAtts = mergedObjects.getObjectById(id);
			modelToViewModelMapper.putView(object, viewAtts);
		}
		VampireDocument vampDoc = Constructors.constructors.createVampireDocument
				(holder.getSheet(), modelToViewModelMapper, pluginDirectory);
		vampDoc.setFileSystem(fileSystem);
		vampDoc.setPath(zipPath);
		if (compressed){
			cleanup();
		}
		return vampDoc;
	}
	
	private void cleanup() throws IOException{
		FileUtils.deleteAll(tmpPath.resolve(importedDocuments+""));
	}
	
	private Path uncompress(Path zipPath) throws IOException{
		return uncompress(zipPath, tmpPath);
	}
	
	private Path uncompress(Path zipPath, Path tmpPath) throws IOException{
		importedDocuments++;
		int i = importedDocuments;
		FileSystem fileSystem = FileSystems.newFileSystem(zipPath,null);
		Path root = fileSystem.getPath("/");
		Path target = tmpPath.resolve(""+i);
		Files.createDirectories(target);
		FileUtils.copyAll(root, target);
		fileSystem.close();
		return target;
	}

	@Override
	public boolean canHandle(Path path) {
		if(path.getFileName().toString().endsWith(".vmpcs")){
			try(FileSystem fileSystem = FileSystems.newFileSystem(path,null);
					URLClassLoader classLoader = 
							new URLClassLoader(new URL[]{fileSystem.getPath("/").toUri().toURL()});){
				Path version = fileSystem.getPath("/version.properties");
				if (!Files.exists(version)){
					return false;
				}
				ResourceBundle bundle = ResourceBundle.getBundle("version", Locale.getDefault(), classLoader);
				String versionString = bundle.getString("version");
				ResourceBundle.clearCache(classLoader);
				return "1.0.0.0".equals(versionString);
			}
			catch (IOException exception){
				return false;
			}
		}
		return false;
	}

	@Override
	public void setModelConstructors(ModelConstructors constructors) {
		Constructors.constructors = constructors;
	}
	
	

}
