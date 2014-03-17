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

/**
 * Provides read-only access to the view attributes of a value.
 * @author rex_victor
 *
 */
public interface ValueViewAttributesAPI extends PublicCloneable{
	
	/**
	 * Returns if a space shall be shown after the first 5 circles.
	 * @return if a space shall be shown
	 */
	public boolean isShowSpace();
	
	/**
	 * Returns if circles can be added during runtime.
	 * @return if the value view is dynamic
	 */
	public boolean isDynamic();
	
	/**
	 * Returns the number of circles to be shown.
	 * @return the number of circles
	 */
	public int getCircles();
	
	/**
	 * Returns if the temporary value shall be displayed as squares.
	 * @return if the temporary value is shown as squares
	 */
	public boolean isTempSquared();
	
	/**
	 * Returns the size of the circles.
	 * @return the circle size
	 */
	public int getSize();
	
	/**
	 * Returns a deep copy of this {@link ValueViewAttributesAPI}.
	 * @return a deep copy
	 */
	@Override
	public ValueViewAttributesAPI clone();

}
