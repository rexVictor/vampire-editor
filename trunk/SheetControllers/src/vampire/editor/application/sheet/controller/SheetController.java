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

import vampire.editor.plugin.api.application.sheet.controller.MiscControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.domain.sheet.Sheet;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.view.sheet.SheetView;


public class SheetController implements SheetControllerAPI {
	
	private final VampireDocumentAPI document;
	
	private final Sheet sheet;
	
	private final SheetView view;
	
	private CategoriesController categoriesController;
	
	private MetaController metaController;
	
	public MetaController getMetaController() {
		return metaController;
	}

	public void setMetaController(MetaController metaController) {
		this.metaController = metaController;
	}

	private MiscControllerAPI miscController;
	
	public SheetController(VampireDocumentAPI document, SheetView view) {
		super();
		this.sheet = (Sheet) document.getSheet();
		this.view = view;
		this.document = document;
	}
	
	
	@Override
	public SheetView getView(){
		return view;
	}
	
	public Sheet getSheet(){
		return sheet;
	}

	public MiscControllerAPI getMiscController() {
		return miscController;
	}

	public void setMiscController(MiscControllerAPI miscController) {
		this.miscController = miscController;
	}

	@Override
	public VampireDocumentAPI getDocument() {
		return document;
	}

	public CategoriesController getCategoriesController() {
		return categoriesController;
	}

	public void setCategoriesController(CategoriesController categoriesController) {
		this.categoriesController = categoriesController;
	}
	
	

}
