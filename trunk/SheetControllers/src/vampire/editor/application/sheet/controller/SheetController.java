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
