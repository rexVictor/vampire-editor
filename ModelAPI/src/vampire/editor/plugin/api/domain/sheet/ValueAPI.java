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

import vampire.editor.plugin.api.domain.sheet.view.PublicCloneable;

/**
 * The ValueAPI offers the methods to get the properties of a value object:<br>
 * <ul>
 * <li> value: The value of the trait as an integer.</li>
 * <li> maxValue:  The maximal integer value can be. </li>
 * <li> minValue: The minimal integer value can be. </li>
 * <li> tempValue: The temporal value of this trait as an integer. <br>
 * The tempValue can be different from value, e.g. because of used blood points or disciplines.
 * </li>
 * </ul>
 * All implementations must check the assertion minValue <= value <= maxValue.
 * @author rex_victor
 *
 */
public interface ValueAPI extends PublicCloneable{

	/**
	 * Returns the value.
	 * @return value
	 */
	public int getValue();

	/**
	 * Returns the maximal value for the value field.
	 * @return maxValue
	 */
	public int getMaxValue();

	/**
	 * Returns the minimal value for the value field.
	 * @return minValue
	 */
	public int getMinValue();
	
	/**
	 * Returns the temporary value.
	 * @return tempValue
	 */
	public int getTempValue();
	
	/**
	 * Returns a deep copy of this Value Object.
	 */
	@Override
	public ValueAPI clone();

}
