/*******************************************************************************
 * Vampire Editor Model View Attributes API.
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
package vampire.editor.plugin.api.domain.sheet.view;

import java.awt.Font;
/**
 * Provides read-only access to the ViewAttributes of a Category.
 * @author rex_victor
 *
 */
public interface CategoryViewAttributesAPI extends DataViewAttributesAPI, PublicCloneable{
	
	/**
	 * Returns if the corresponding category shall be displayed with a separation line.
	 * @return if the line shall be shown
	 */
	public boolean isShowLine();
	
	/**
	 * Returns the title to be shown in the line.
	 * @return the title.
	 */
	public String getTitle();
	
	/**
	 * Returns the key of the image of the line. Read more about this in the ResourcesAPI.
	 * @return the key of the image
	 */
	public String getImage();
	
	/**
	 * Returns the font, the title is printed with.
	 * @return the font of the title
	 */
	public Font getFont();
	
	/**
	 * Returns a deep copy of this {@link CategoryViewAttributesAPI}
	 */
	@Override
	public CategoryViewAttributesAPI clone();

}
