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

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import vampire.editor.plugin.api.domain.DictionaryAPI;

/**
 * The implementaton of the {@link DictionaryAPI} interface.
 * @author rex
 *
 */
public class Dictionary implements DictionaryAPI {
	
	private final Map<String, String> valueToKey = new HashMap<>();
	
	private final Map<String, String> keyToValue = new HashMap<>();
	
	private final String name;
	
	public Dictionary(ResourceBundle bundle, String name) {
		super();
		this.name = name;
		initialize(bundle);
	}
	
	private void initialize(ResourceBundle bundle){
		if (bundle == null) return;
		Set<String> keys = bundle.keySet();
		for (String key : keys){
			String value = bundle.getString(key);
			keyToValue.put(key, value);
			valueToKey.put(value, key);
		}
	}	
	

	@Override
	public String getKey(String value){
		if (value.isEmpty()) return value;
		String toReturn = valueToKey.get(value);
		if (toReturn == null)
			return value;
		return toReturn;
		
	}
	
	@Override
	public String getValue(String key){
		String toReturn = keyToValue.get(key);
		if (toReturn == null)
			return key;
		return toReturn;
	}

	public String getName() {
		return name;
	}
	
	
	
	
	
	
	
	

}
