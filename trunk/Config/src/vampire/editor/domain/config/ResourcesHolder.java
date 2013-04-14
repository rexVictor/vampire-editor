/*******************************************************************************
 * Vampire Editor Config.
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
package vampire.editor.domain.config;

import java.awt.Image;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;

import vampire.editor.domain.Border;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;

public class ResourcesHolder implements ResourcesHolderAPI{
	
	private final Map<String, Border> borders;
	
	private final Map<String, Image> lines;
	
	private final Map<String, Dictionary> dictionaries;
	
	private final Map<String, Path> defaultSheets;
	
	

	public ResourcesHolder(Map<String, Border> borders,
			Map<String, Image> lines, Map<String, Dictionary> dictionaries,
			Map<String, Path> defaultSheets) {
		super();
		this.borders = borders;
		this.lines = lines;
		this.dictionaries = dictionaries;
		this.defaultSheets = defaultSheets;
	}

	@Override
	public DictionaryAPI getDictionary(String key) {
		DictionaryAPI dictionary = dictionaries.get(key);
		if (dictionary == null){
			return new Dictionary(null, "");
		}
		return dictionary;
	}
	
	public Border getBorder(String key){
		return borders.get(key);
	}
	
	@Override
	public Image getLine(String key){
		return lines.get(key);
	}

	public Path getDefaultSheet(String key){
		return defaultSheets.get(key);
	}
	
	public Map<String, Path> getDefaultSheets(){
		return Collections.unmodifiableMap(defaultSheets);
	}
	


}
