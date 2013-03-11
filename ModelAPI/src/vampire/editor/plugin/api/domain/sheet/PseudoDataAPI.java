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
 * The pseudo "mother" interface of many interfaces in this package. <br>
 * Because of the java generics those intefaces do extend this interface instead of {@link DataAPI}.
 * But all classes implementing this interface, must also implement {@link DataAPI}.
 * <br>
 * All implementations of this interface are specified to manage the children, which have the the parameter type.
 * @author rex_victor
 *
 * @param <V>
 */
interface PseudoDataAPI<V extends Nameable> extends Nameable{
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
