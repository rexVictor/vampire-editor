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
		Map<Class<?>, String> map = new HashMap<>();
		map.put(Font.class, FileNames.FONTS);
		map.put(LineAttributes.class, FileNames.LINES);
		map.put(ValueViewAttributes.class, FileNames.VALUEVIEWATTS);
		map.put(TraitViewAttributes.class, FileNames.TRAITVIEWATTS);
		map.put(SubCategoryViewAttributes.class, FileNames.SUBCATEGORYVIEWATTS);
		map.put(CategoryViewAttributes.class, FileNames.CATEGROYVIEWATTS);
		map.put(MetaEntryViewAttributes.class, FileNames.METAENTRYVIEWATTS);
		map.put(HealthEntryViewAttributes.class, FileNames.HEALTENTRYVIEWATTS);
		map.put(HealthViewAttributes.class, FileNames.HEALTHVIEWATTS);
		map.put(MeritViewAttributes.class, FileNames.MERITVIEWATTS);
		map.put(MeritEntryViewAttributes.class, FileNames.MERITENTRYVIEWATTS);
		map.put(BloodPoolViewAttributes.class, FileNames.BLOODPOOLVIEWATTS);
		map.put(Sheet.class, FileNames.SHEET);
		map.put(Value.class, FileNames.VALUES);
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
