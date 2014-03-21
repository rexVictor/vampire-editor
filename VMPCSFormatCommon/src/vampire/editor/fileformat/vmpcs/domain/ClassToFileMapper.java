/*******************************************************************************
 * Vampire Editor Importer: VMPCS: Commons.
 * Copyright (C) 2013  Matthias Johannes Reimchen
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
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.fileformat.vmpcs.domain;

import java.awt.Font;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import vampire.editor.plugin.api.domain.sheet.Sheet;
import vampire.editor.plugin.api.domain.sheet.Value;
import vampire.editor.plugin.api.domain.sheet.view.*;

public class ClassToFileMapper {
	
	static
	{
		String fonts = "fonts.json";
		String lines = "lines.json";
		String valueViewAtts = "valueviewatts.json";
		String traitViewAtts = "traitviewatts.json";
		String subCategoryViewAtts = "subcategoryviewatts.json";
		String categroyViewAtts = "categoryviewatts.json";
		String metaEntryViewAtts = "metaentryviewatts.json";
		String bloodPoolViewAtts = "bloodpoolviewatts.json";
		String healtEntryViewAtts = "healthentryviewatts.json";
		String healthViewAtts = "healthviewatts.json";
		String meritViewAtts = "meritviewatts.json";
		String meritEntryViewAtts = "meritentryviewatts.json";
		String sheet = "sheet.json";
		String values = "values.json";
		
		Map<Class<?>, String> map = new HashMap<>();
		map.put(Font.class, fonts);
		map.put(LineAttributes.class, lines);
		map.put(ValueViewAttributes.class, valueViewAtts);
		map.put(TraitViewAttributes.class, traitViewAtts);
		map.put(SubCategoryViewAttributes.class, subCategoryViewAtts);
		map.put(CategoryViewAttributes.class, categroyViewAtts);
		map.put(MetaEntryViewAttributes.class, metaEntryViewAtts);
		map.put(HealthEntryViewAttributes.class, healtEntryViewAtts);
		map.put(HealthViewAttributes.class, healthViewAtts);
		map.put(MeritViewAttributes.class, meritViewAtts);
		map.put(MeritEntryViewAttributes.class, meritEntryViewAtts);
		map.put(BloodPoolViewAttributes.class, bloodPoolViewAtts);
		map.put(Sheet.class, sheet);
		map.put(Value.class, values);
		paths = Collections.unmodifiableMap(map);		
	}
	
	public static final Map<Class<?>, String> paths;
	
	public static String getFileName(Class<?> clazz){
		Set<Class<?>> classes = paths.keySet();
		for (Class<?> c : classes){
			if (c.isAssignableFrom(clazz)){
				return paths.get(c);
			}
		}
		return null;
	}

}
