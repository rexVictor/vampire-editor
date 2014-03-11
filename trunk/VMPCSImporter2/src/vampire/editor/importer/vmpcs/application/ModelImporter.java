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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.fileformat.vmpcs.domain.MapId;
import vampire.editor.fileformat.vmpcs.domain.ModelToViewMap;
import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;
import vampire.editor.fileformat.vmpcs.domain.ProtoTrait;
import vampire.editor.fileformat.vmpcs.domain.ProtoValue;
import vampire.editor.fileformat.vmpcs.domain.ToRealModelTransformable;
import vampire.editor.plugin.api.domain.sheet.Sheet;
import vampire.editor.plugin.api.domain.sheet.Trait;
import vampire.editor.plugin.api.domain.sheet.Value;

public class ModelImporter {
	
	private ObjectMapper mapper = new ObjectMapper();
	
	public ProtoSheet loadSheet(Path path) throws JsonParseException, JsonMappingException, IOException{
		InputStream stream = Files.newInputStream(path);
		return mapper.readValue(stream, ProtoSheet.class);
	}
	
	public SheetAndMapIdHolder buildSheet(ProtoSheet protoSheet, Map<Integer, Object> mapIdMap, Objects<Value> realValues){
		Sheet sheet = protoSheet.toRealModel();
		Map<Integer, Object> mapIdToRealModelMap = new HashMap<>();
		Set<Integer> mapIds = mapIdMap.keySet();
		for (Integer i : mapIds){
			Object current = mapIdMap.get(i);
			if (current instanceof ProtoTrait){
				ProtoTrait protoTrait = (ProtoTrait) current;
				ProtoValue protoValue = protoTrait.getValue();
				Trait trait = protoTrait.toRealModel();
				Value value = realValues.getObjectByID(protoValue.getId());
				trait.setValue(value);
				mapIdToRealModelMap.put(protoValue.getMapid(), value);
			}
			if (current instanceof MapId && current instanceof ToRealModelTransformable<?>){
				MapId mapIdObject = (MapId) current;
				ToRealModelTransformable<?> transformable = (ToRealModelTransformable<?>) current;
				mapIdToRealModelMap.put(mapIdObject.getMapid(), transformable.toRealModel());
			}
		}
		return new SheetAndMapIdHolder(sheet, mapIdToRealModelMap);
	}
	
	public ModelToViewMap buildProtoIdMap(Path path) throws JsonParseException, JsonMappingException, IOException{
		ModelToViewMap map = new ModelToViewMap();
		Properties properties = new Properties();
		try(InputStream stream = Files.newInputStream(path.resolve("modeltoviewmap.properties"))){
			properties.load(stream);
			Set<Object> keys = properties.keySet();
			for (Object o : keys){
				String s = (String) o;
				Integer mapid = Integer.parseInt(s);
				String value = properties.getProperty(s);
				Integer id = Integer.parseInt(value);
				map.put(mapid, id);
			}
		}
	/*	try(URLClassLoader classLoader = new URLClassLoader(new URL[]{path.toUri().toURL()})){
			ResourceBundle bundle = ResourceBundle.getBundle("modeltoviewmap", Locale.getDefault(), classLoader);
			Set<String> keys = bundle.keySet();
			for (String s : keys){
				Integer mapid = Integer.parseInt(s);
				String value = bundle.getString(s);
				Integer id = Integer.parseInt(value);
				map.put(mapid, id);
			}
			ResourceBundle.clearCache(classLoader);
		}*/
		return map;
	}
	
}
