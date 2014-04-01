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

import vampire.editor.plugin.api.domain.sheet.MetaEntry;


/**
 * A meta entry is a pair of {@link String}s, respectively a key and a value.<br>
 * Examples for meta entries are name, generation, haven and demeanor with their corresponding values.
 * @author rex_victor
 *
 */
class MMetaEntry implements MetaEntry {
	
	private String name;
	
	private String value;
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public MMetaEntry clone(){
		MMetaEntry clone = new MMetaEntry();
		clone.name = name;
		clone.value = value;
		return clone;
	}
	
	@Override
	public String toString(){
		return name + " : " + value; //$NON-NLS-1$
	}
}
