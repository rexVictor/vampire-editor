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

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vampire.editor.fileformat.vmpcs.domain.ClassToFileMapper;
import vampire.editor.importer.vmpcs.domain.MergedObjects;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.view.LineAttributes;

public class ObjectsLoader {
	
	private final ResourcesHolderAPI resourcesHolder;
	
	public ObjectsLoader(ResourcesHolderAPI resourcesHolder) {
		super();
		this.resourcesHolder = resourcesHolder;
	}

	public MergedObjects loadObjects(Path root) throws JsonParseException, JsonMappingException, IOException{
		MergedObjects mergedObjects = new MergedObjects();
		Map<Class<?>, String> map = ClassToFileMapper.paths;
		Objects<Font> fonts = new Objects<>(root, Font.class,resourcesHolder, null, null);
		Objects<LineAttributes> lineAttributes = new Objects<>(root, LineAttributes.class, resourcesHolder, null, null);
		Set<Class<?>> classesSet = map.keySet();
		Set<Class<?>> viewAtts = new HashSet<>();
		for (Class<?> clazz : classesSet){
			String value = map.get(clazz);
			if (value.endsWith("viewatts.json")){ //$NON-NLS-1$
				viewAtts.add(clazz);
			}
		}
		for (Class<?> clazz : viewAtts){
			Objects<?> objects = new Objects<>(root, clazz, resourcesHolder, fonts, lineAttributes);
			mergedObjects.addObjects(objects);
		}
		return mergedObjects;
	}

}
