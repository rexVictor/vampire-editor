/*******************************************************************************
 * Vampire Editor Model Implementation.
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
package vampire.editor.domain.sheet;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.Set;

import vampire.editor.plugin.api.domain.sheet.*;

/**
 * The implementation of {@link ModelToViewModelMapperAPI}.
 * It maps all model objects to the according ViewAttributes object.
 * @author rex_victor
 *
 */
class MModelToViewModelMapper implements ModelToViewModelMapper{
	
	private final Map<Object, Object> associations = new IdentityHashMap<>();
	
	public void putView(Object model, Object view){
		if (associations.containsKey(model) || associations.containsValue(view)){
			if (associations.get(model) != view){
				throw new DuplicateModelViewPairException("Already inserted!\n"+
						model + " or " + view);
			}
		}
		associations.put(model, view);
	}
	
	public Object getViewAttributes(Object object){
		return associations.get(object);
	}
	

	@Override
	public void removeView(MeritAPI merit) {
		associations.remove(merit);
	}

	@Override
	public void removeView(ValueAPI value) {
		associations.remove(value);
	}

	@Override
	public void removeView(TraitAPI trait) {
		associations.remove(trait);
	}
	
	public Set<Object> keySet(){
		return associations.keySet();
	}
	
	@Override
	public String toString(){
		return associations.toString();
	}

}
