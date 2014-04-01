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
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vampire.editor.fileformat.vmpcs.domain.ClassToFileMapper;
import vampire.editor.fileformat.vmpcs.domain.Constructors;
import vampire.editor.fileformat.vmpcs.domain.StringConstants;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.api.domain.sheet.Value;
import vampire.editor.plugin.api.domain.sheet.ValueAPI;
import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.FontSettable;
import vampire.editor.plugin.api.domain.sheet.view.LineAttributes;
import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.PublicCloneable;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class Objects<V> {

	private final Map<Integer, V> values = new HashMap<>();
	
	private final Objects<Font> fonts;
	
	private final Objects<LineAttributes> lineAtts;
	
	private static ObjectMapper mapper = null;
	
	static{
		mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Font.class, new FontDeserializer());
		Map<Class<?>, Class<?>> map = Constructors.viewAttConstructors.getInterfaceToImplementationMap();
		Set<Class<?>> classes = map.keySet();
		for (Class<?> key : classes){
			initializeModule(key, map.get(key), module);
		}
		module.addAbstractTypeMapping(Value.class, Constructors.constructors.createValue(0, 10).getClass());
		mapper.registerModule(module);
	}
	
	private static <C> void initializeModule(Class<?> clazz1, Class<?> clazz2, SimpleModule module){
		@SuppressWarnings("unchecked")
		Class<C> class1 = (Class<C>) clazz1;
		@SuppressWarnings("unchecked")
		Class<? extends C> class2 = (Class<? extends C>) clazz2;
		module.addAbstractTypeMapping(class1, class2);
	}
	
	
	
	public Objects(Class<? extends V> clazz, Path file, ResourcesHolderAPI resources,
					Objects<Font> fonts, Objects<LineAttributes> lineAtts)
							throws JsonParseException, JsonMappingException, IOException{
		this.fonts = fonts;
		this.lineAtts = lineAtts;
		load(file, clazz);
	}
	
	public Objects(Path root, Class<? extends V> clazz, ResourcesHolderAPI resources,
					Objects<Font> fonts, Objects<LineAttributes> lineAtts)
							throws JsonParseException, JsonMappingException, IOException{
		Path file = root.resolve(ClassToFileMapper.paths.get(clazz));
		this.fonts = fonts;
		this.lineAtts = lineAtts;
		load(file, clazz);
	}
	
	private void load(Path file, Class<? extends V> clazz) throws JsonParseException, JsonMappingException, IOException{
		List<Map<String, Object>> values = null;
		try(InputStream stream = Files.newInputStream(file)){
			values = mapper.readValue(stream, new TypeReference<List<Map<String, Object>>>() {});
		}
		for (Map<String, Object> o : values){
			int id = (int) o.remove(StringConstants.ID);
			Integer fontId = (Integer) o.remove(StringConstants.FONT);
			Integer titleID = (Integer) o.remove(StringConstants.TITLE_FONT);
			Integer contentID = (Integer) o.remove(StringConstants.CONTENT_FONT);
			Integer lineID = (Integer) o.remove(StringConstants.LINE);
			V value = mapper.convertValue(o, clazz);
			if (fontId != null)
				if (value instanceof FontSettable)
					((FontSettable) value).setFont(fonts.getObjectByID(fontId));
			if (value instanceof MetaEntryViewAttributes){
				MetaEntryViewAttributes meta = (MetaEntryViewAttributes) value;
				meta.setContentFont(fonts.getObjectByID(contentID));
				meta.setTitleFont(fonts.getObjectByID(titleID));
			}
			if (value instanceof CategoryViewAttributes){
				CategoryViewAttributes catViewAtts = (CategoryViewAttributes) value;
				catViewAtts.setLine(lineAtts.getObjectByID(lineID));
			}
			this.values.put(id, value);
		}
	
	}
	
	@SuppressWarnings("unchecked")
	public V getObjectByID(int id){
		V v = values.get(id);
		if (v == null)
			return null;
		if (v instanceof Nameable)
			return (V) ((Nameable) v).clone();
		if (v instanceof PublicCloneable)
			return (V) ((PublicCloneable) v).clone();
		if (v instanceof ValueAPI){
			return (V) ((Value) v).clone();
		}
		return v;
		
	}
	
	public Set<Integer> getKeySet(){
		return values.keySet();
	}

}
