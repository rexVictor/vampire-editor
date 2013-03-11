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
 * Merit stores the cost and the name of a merit. <br>
 * Note: There is no difference between a merit and a flaw in the model. <br>
 * Merits have positive cost and flaws have negative cost.
 * @author rex_victor
 */
public interface MeritAPI extends Nameable{

	/**
	 * Returns the cost of this merit. If it is a flaw the cost is negative, else it is positve.
	 * @return the cost of this merit.
	 */
	public int getCost();

	/**
	 * Return the name of this merit.
	 */
	@Override
	public String getName();
	
	/**
	 * Returns a deep copy of this merit.
	 * @return a deep copy.
	 */
	@Override
	public MeritAPI clone();

}
