package vampire.editor.plugin.api.application.sheet.controller;

import java.util.Iterator;

import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.view.sheet.SheetView;


public interface SheetControllerAPI {

	public void addCategoryController(CategoryControllerAPI controller);
	
	public SheetView getView();
	
	public MetaControllerAPI getMetaController();
	
	public MiscControllerAPI getMiscController();
	
	public VampireDocumentAPI getDocument();
	
	public Iterator<? extends CategoryControllerAPI> getCategoryIterator();
	
}