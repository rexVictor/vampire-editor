/*******************************************************************************
 * Vampire Editor Model API with write methods.
 * Copyright (C) 2014  Matthias Johannes Reimchen
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
package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;

public interface Category extends CategoryAPI, Iterable<SubCategory>{
	
	public int size();
	
	public Iterator<? extends SubCategory> getIterator();
	
	public Category clone();
	
	public void add(SubCategory subCategory);
	
	public void insert(int pos, SubCategory subCat);
	
	public void remove(SubCategory subCat);
	
	public void setName(String name);
}
