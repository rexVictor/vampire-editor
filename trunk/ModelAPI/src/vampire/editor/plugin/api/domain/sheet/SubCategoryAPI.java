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
 * The SubCategoryAPI is an interface specializing the {@link DataAPI} interface. <br> 
 * Examples for subcategories are the mental attributes, physical attributes or the knowledges. <br>
 * It provides an {@link Iterator} to get its attached Traits ({@link TraitAPI}).<br>
 * <br>
 * Implementation Note: <br>
 * This interface does not extend the {@link DataAPI} interface itself, due to problems with Java Generics. <br>
 * But all classes implementing this interface, must also implement the {@link DataAPI} interface. <br>
 * Nevertheless this interface demands all methods demanded by {@link DataAPI}.
 * @author rex_victor
 *
 */

public interface SubCategoryAPI extends Nameable, HasIterator<TraitAPI>{
	
	/**
	 * This implementation is optional
	 */
	public SubCategoryAPI clone();

	public String getName();
	
	/**
	 * @return the count of added elements.
	 */
	public int size();

	
}
