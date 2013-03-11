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

import java.awt.Font;
import java.awt.Image;
import java.nio.file.Path;
import java.util.Map;

/**
 * The interface providing the common resources.
 * @author rex_victor
 *
 */
public interface ResourcesHolderAPI {
	
	public Font getFont(String key);
	
	/**
	 * Returns the dictionary with the specified name.
	 * @param key
	 * @return dictionary
	 * @see DictionaryAPI
	 */
	public DictionaryAPI getDictionary(String key);
	
	public Image getLine(String key);
	
	/**
	 * Returns the key of the font as given in the configuration.<br>
	 * (Needed for export).
	 * @param font
	 * @return the key of the font
	 */
	public String getKeyOfFont(Font font);
	
	/**
	 * Returns the border with the specified name.
	 * @param key
	 * @return border
	 * @see BorderAPI
	 */
	public BorderAPI getBorder(String key);
	
	/**
	 * Returns a map with all paths pointing to an empty sheet, as specified in the configuration.
	 * @return a map with paths to empty sheets
	 */
	public Map<String, Path> getDefaultSheets();
	
}
