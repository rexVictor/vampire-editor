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

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import vampire.editor.plugin.api.domain.sheet.Nameable;

@SuppressWarnings("synthetic-access")
class MSorter<V extends Nameable> {
	
	private final Comparator<String> stringComparator;
	
	private class DataComparator implements Comparator<V>{

		
		@Override
		public int compare(V o1, V o2) {
			return stringComparator.compare(o1.getName(), o2.getName());
		}
		
	}
	
	private final Comparator<V> comparator = new DataComparator();
	

	public MSorter(Comparator<String> stringComparator) {
		super();
		this.stringComparator = stringComparator;
	}
	
	public void sort(List<? extends V> list){
		Collections.sort(list, comparator);
	}
	
	

}
