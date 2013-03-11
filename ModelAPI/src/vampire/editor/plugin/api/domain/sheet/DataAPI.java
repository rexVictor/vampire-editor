/*******************************************************************************
 * Vampire Editor Model API.
 *     Copyright (C) 2013  Matthias Johannes Reimchen
 * 
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 * 
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *     
 *     Further information can be obtained at
 *     https://code.google.com/p/vampire-editor/ or via mail:
 *     Matthias Johannes Reimchen
 *     development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.plugin.api.domain.sheet;

import java.util.Iterator;
/**
 * The "mother" interface of many interfaces in this package. <br>
 * Because of the java generics those intefaces do not extend this interfaces. But all classes implementing those
 * interfaces, must also implement this one.
 * <br>
 * All implementations of this interface are specified to manage the children, which have the the parameter type.
 * @author rex_victor
 *
 * @param <V>
 */
public interface DataAPI<V extends Nameable> extends Nameable, Iterable<V>{
	
	/**
	 * This implementation is optional
	 */
	@Override
	public DataAPI<? extends V> clone();

	/**
	 * @return an {@link Iterator} over the parametrized children of type V
	 */
	public Iterator<? extends V> getIterator();
	
	@Override
	public String getName();
	
	/**
	 * @return the count of added elements.
	 */
	public int size();

}
