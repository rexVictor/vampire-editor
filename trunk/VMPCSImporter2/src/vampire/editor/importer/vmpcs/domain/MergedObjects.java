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
package vampire.editor.importer.vmpcs.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import vampire.editor.importer.vmpcs.application.Objects;

public class MergedObjects {
	
	private Map<Integer, Objects<?>> objectsMap = new HashMap<>();
	
	public Object getObjectById(Integer id){
		return objectsMap.get(id).getObjectByID(id);
	}
	
	public void addObjects(Objects<?> objects){
		Set<Integer> keys = objects.getKeySet();
		for (Integer i : keys){
			objectsMap.put(i, objects);
		}
	}

}