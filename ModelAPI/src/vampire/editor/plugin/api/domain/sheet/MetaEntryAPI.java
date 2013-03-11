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

/**
 * A meta entry is a pair of {@link String}s, respectively a key and a value.<br>
 * Examples for meta entries are name, generation, haven and demeanor with their corresponding values.
 * @author rex_victor
 *
 */
public interface MetaEntryAPI extends Nameable{

	/**
	 * @return the name of this meta entry, respectively the key.
	 */
	@Override
	public String getName();

	/**
	 * @return the value of this meta entry.
	 */
	public String getValue();

	/**
	 * Returns a deep copy of this. 
	 * @return a deep copy of this {@link MetaEntryAPI}.
	 */
	@Override
	public MetaEntryAPI clone();	

}
