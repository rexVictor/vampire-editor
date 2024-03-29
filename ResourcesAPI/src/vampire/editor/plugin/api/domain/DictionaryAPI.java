/*******************************************************************************
 * Vampire Editor Resources API.
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
package vampire.editor.plugin.api.domain;

/**
 * The interface offering a dictionary as part of the internationalization.<br>
 * It also provides the possibility, to get the key of a value.
 * @author rex_victor
 *
 */
public interface DictionaryAPI {
	
	public String getValue(String key);
	
	public String getKey(String value);
	
	public static final String SHEET_DICTIONARY = "sheet"; //$NON-NLS-1$
	
	public static final String GENERAL_DICTIONARY = "general"; //$NON-NLS-1$

}
