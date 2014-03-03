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
 * This interface is the root of the model without view attributes. It provides all methods to access
 * the whole model.
 * @author rex_victor
 *
 */
public interface SheetAPI {
	
	/**
	 * Optional Operation
	 * @return
	 */
	public SheetAPI clone();

	/**
	 * @return the meta information of the sheet
	 * @see MetaAPI
	 */
	public MetaAPI getMeta();
	
	/**
	 * @return the cateogries of the sheet
	 * @see CategoryAPI
	 */
	public CategoriesAPI getCategories();
	
	/**
	 * @return the blood pool of the sheet
	 * @see BloodPoolAPI
	 */
	public BloodPoolAPI getBloodPool();
	
	/**
	 * @return the health of the sheet
	 * @see HealthAPI
	 */
	public HealthAPI	getHealth();
	
	/**
	 * @return the merits of the sheet
	 * @see MeritsAPI
	 */
	public MeritsAPI	getMerits();
	
	/**
	 * @return the flaws of the sheet
	 * @see MeritsAPI
	 */
	public MeritsAPI	getFlaws();
	
	/**
	 * Consider the ResourcesAPI of the editor for this method.
	 * @return the key of the border, as defined in the configuration
	 */
	public String getBorderKey();

}
