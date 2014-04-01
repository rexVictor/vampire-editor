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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ModelToViewMap {

	private final Map<Integer, Integer> mapIdToId = new HashMap<>();
	
	public ModelToViewMap(){
		
	}
	
	public Integer getViewAttIdOf(Integer mapId){
		return mapIdToId.get(mapId);
	}
	
	public void put(Integer mapid, Integer id){
		if (mapIdToId.containsKey(mapid)){
			throw new DuplicateIdPairException(mapid + "already inserted!"); //$NON-NLS-1$
		}
		mapIdToId.put(mapid, id);
	}
	
	@Override
	public String toString(){
		return mapIdToId.toString();
	}
	
	public Set<Integer> keySet(){
		return Collections.unmodifiableSet(mapIdToId.keySet());
	}
	
}
