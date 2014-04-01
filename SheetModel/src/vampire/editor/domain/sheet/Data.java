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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vampire.editor.plugin.api.domain.sheet.Nameable;

/**
 * Implementation of {@link DataAPI}.<br>
 * It stores {@link Nameable} elements of Type W.<br>
 * Furthermore this is {@link Iterable} to obtain the added elements.
 * @author rex_victor
 *
 * @param <W>
 */
@SuppressWarnings("synthetic-access")
class Data<W extends Nameable> implements Iterable<W>{
	
	/**
	 * An iterator for the {@link Data} class.<br>
	 * The remove method will cause an {@link UnsupportedOperationException}.
	 * @author rex_victor
	 *
	 */
	private class DataIterator implements Iterator<W>{
		
		private Iterator<W> iterator = entries.iterator();
		
		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public W next() {
			return iterator.next();
		}
		
		/**
		 * Throws an {@link UnsupportedOperationException}
		 * @throws UnsupportedOperationException
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
			
		}
		
	}
	
	
	private final List<W> entries = new ArrayList<>();
	
	private String name;
	
	public Data() {
		super();
	}
	
	public void add(W v){
		entries.add(v);
	}

	
	/**
	 * Returns a deep copy of this object.
	 * @return a deep copy
	 */
	@Override
	@SuppressWarnings("unchecked")
	public Data<W> clone(){
		Data<W> data = new Data<>();
		data.name = this.name;
		for (W v : entries){
			data.entries.add((W) v.clone());
		}
		return data;
	}
	
	public Iterator<W> getIterator(){
		return new DataIterator();
	}
	
	public String getName() {
		return name;
	}
	/**
	 * Inserts an Element of type W into position i.
	 * @param i
	 * @param v
	 */
	public void insert(int i, W v){
		entries.add(i, v);
	}
	
	/**
	 * Removes the element v from this {@link Data} object.
	 * @param v
	 */
	public void remove(W v){
		entries.remove(v);
	}
	
	/**
	 * Sets the name of this {@link Data} object.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString(){
		return name+" : " +entries.toString(); //$NON-NLS-1$
	}

	/**
	 * Returns an {@link Iterator} over the elements, that does not provide the
	 * remove method.
	 */
	@Override
	public Iterator<W> iterator() {
		return new DataIterator();
	}
	
	public int size(){
		return entries.size();
	}
	
	/**
	 * Returns the element it position i.
	 * @param i
	 * @return i-th element
	 */
	public W get(int i){
		return entries.get(i);
	}
	
	/**
	 * Returns the index of the element v.
	 * @param v
	 * @return index of v
	 */
	public int indexOf(W v){
		return entries.indexOf(v);
	}

}
