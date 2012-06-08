package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.view.sheet.SheetView;


public interface SheetControllerAPI {

	public void addCategoryController(CategoryControllerAPI controller);
	
	public SheetView getView();

}