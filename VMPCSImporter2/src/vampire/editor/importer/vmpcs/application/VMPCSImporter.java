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
import java.io.InputStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import vampire.editor.fileformat.vmpcs.domain.Constructors;
import vampire.editor.fileformat.vmpcs.domain.FileNames;
import vampire.editor.fileformat.vmpcs.domain.MapidResolver;
import vampire.editor.fileformat.vmpcs.domain.ModelToViewMap;
import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;
import vampire.editor.fileformat.vmpcs.domain.StringConstants;
import vampire.editor.importer.vmpcs.domain.MergedObjects;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.ModelConstructors;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapper;
import vampire.editor.plugin.api.domain.sheet.Value;
import vampire.editor.plugin.api.domain.sheet.VampireDocument;
import vampire.editor.plugin.api.domain.sheet.view.ViewAttConstructors;
import vampire.editor.plugin.api.importer.DocumentImportException;
import vampire.editor.plugin.api.importer.SheetImporter;

public class VMPCSImporter implements SheetImporter{
	
	private final ResourcesHolderAPI resourcesHolder;
	
	private final MapidResolver mapidResolver = new MapidResolver();
	
	private final ObjectsLoader objectsLoader;
	
	public VMPCSImporter(ResourcesHolderAPI resourcesHolder) {
		super();
		this.resourcesHolder = resourcesHolder;
		this.objectsLoader = new ObjectsLoader(resourcesHolder);
	}

	@Override
	public VampireDocument loadDocument(Path path) throws DocumentImportException{
		try {
			return loadDocument(path, false);
		} catch (IOException e) {
			throw new VMPCSImportException(e);
		}
	}
	
	public VampireDocument loadDocument(Path p, boolean compressed) throws IOException{
		Path path = p;
		Path zipPath = path;
		try(FileSystem fileSystem = FileSystems.newFileSystem(zipPath,null)){
			Path pluginDirectory = fileSystem.getPath(FileNames.ROOT, FileNames.PLUGINS);
			path = fileSystem.getPath(FileNames.ROOT);
			MergedObjects mergedObjects = objectsLoader.loadObjects(path);
			ProtoSheet protoSheet = ModelImporter.loadSheet(path.resolve(FileNames.SHEET));
			Map<Integer, Object> protoMapIdMap = mapidResolver.generateMapIdMap(protoSheet);
			ModelToViewMap modelToViewMap = ModelImporter.buildProtoIdMap(path);
			Objects<Value> realValues = new Objects<>(path, Value.class, resourcesHolder, null, null);
			SheetAndMapIdHolder holder = ModelImporter.buildSheet(protoSheet, protoMapIdMap, realValues);
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
			return vampDoc;
		}
		
	}
	
	@Override
	public boolean canHandle(Path path) {
		if(path.getFileName().toString().endsWith(StringConstants.FORMAT)){
			try(FileSystem fileSystem = FileSystems.newFileSystem(path,null)){
				Path version = fileSystem.getPath(FileNames.ROOT);
				version = version.resolve(FileNames.VERSION);
				if (!Files.exists(version)){
					return false;
				}
				try (InputStream stream = Files.newInputStream(version)){
					Properties properties = new Properties();
					properties.load(stream);
					String versionString = properties.getProperty(StringConstants.VERSION);
					return "1.0.0.0".equals(versionString); //$NON-NLS-1$
				}
				
			}
			catch (IOException exception){
			}
		}
		return false;
	}

	@Override
	public void setModelConstructors(ModelConstructors constructors, ViewAttConstructors viewAttConstructors) {
		Constructors.constructors = constructors;
		Constructors.viewAttConstructors = viewAttConstructors;
	}
	
}
