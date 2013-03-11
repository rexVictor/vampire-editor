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
 * Provides read-only access to the view attributes of a trait.
 * @author rex_victor
 *
 */
public interface TraitViewAttributesAPI extends PublicCloneable{
	
	/**
	 * Returns the orientation of the trait view.
	 * @return the orientation
	 * @see Orientation
	 */
	public Orientation getOrientation();
	
	/**
	 * Returns if the name of the trait may be edited.
	 * @return if the name may be edited.
	 */
	public boolean isEditable();
	
	/**
	 * Returns a deep copy of this {@link TraitViewAttributesAPI}.
	 */
	@Override
	public TraitViewAttributesAPI clone();
	
	public Font getFont();

}
