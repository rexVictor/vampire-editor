/*******************************************************************************
 * Vampire Editor SheetControllers.
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
package vampire.editor.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.controller.CategoriesControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ControllerVisitor;
import vampire.editor.plugin.api.application.sheet.events.CategoriesEvent;
import vampire.editor.plugin.api.application.sheet.events.CategoriesListener;
import vampire.editor.plugin.api.domain.sheet.Categories;
import vampire.editor.plugin.api.domain.sheet.Category;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.view.sheet.CategoriesView;
import vampire.editor.plugin.api.view.sheet.CategoryView;

public class CategoriesController extends AbstractNonLeafController<Categories, CategoriesView, CategoriesListener, CategoryControllerAPI, CategoryAPI, Category, CategoryView, CategoriesEvent>
								implements CategoriesControllerAPI{
	
	

	public CategoriesController(Categories m, CategoriesView v) {
		super(m, v);
	}

	@Override
	public void accept(ControllerVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	protected CategoriesEvent generateEvent(CategoryControllerAPI reason,
			int index) {
		return null;
	}
	
}
