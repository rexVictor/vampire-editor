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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vampire.editor.fileformat.vmpcs.domain.MapId;
import vampire.editor.fileformat.vmpcs.domain.MapIdChilds;
import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;

public class MapidResolver {
	
	public Map<Integer, Object> generateMapIdMap(ProtoSheet sheet){
		Map<Integer, Object> soFar = new HashMap<>();
		iterate(sheet, soFar);
		return soFar;
	}
	
	private void iterate(Object toCheck, Map<Integer, Object> soFar){
		if (toCheck instanceof MapId){
			MapId mapId = (MapId) toCheck;
			soFar.put(mapId.getMapid(), mapId);
		}
		if (toCheck instanceof MapIdChilds){
			MapIdChilds mapIdChilds = (MapIdChilds) toCheck;
			List<? extends MapId> list = mapIdChilds.getMapIdChilds();
			for (MapId mapId : list){
				iterate(mapId, soFar);
			}
		}
	}
}
