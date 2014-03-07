/*******************************************************************************
 * Vampire Editor Sheet Events implementation.
 * Copyright (C) 2014  Matthias Johannes Reimchen
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
package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.CategoryEventAPI;

public class CategoryEvent implements CategoryEventAPI{
	
	private final CategoryControllerAPI source;
	
	private final SubCategoryControllerAPI reason;
	
	private final int position;
	
	

	public CategoryEvent(CategoryControllerAPI source,
			SubCategoryControllerAPI reason, int position) {
		super();
		this.source = source;
		this.reason = reason;
		this.position = position;
	}
	
	



	public SubCategoryControllerAPI getReason() {
		return reason;
	}





	public int getPosition() {
		return position;
	}





	public CategoryControllerAPI getSource() {
		return source;
	}

}