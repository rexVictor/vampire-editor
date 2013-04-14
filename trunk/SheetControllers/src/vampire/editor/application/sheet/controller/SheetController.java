package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MiscControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.domain.sheet.Sheet;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.view.sheet.SheetView;


public class SheetController implements SheetControllerAPI {
	
	private final VampireDocumentAPI document;
	
	private final Sheet sheet;
	
	private final SheetView view;
	
	private final List<CategoryControllerAPI> categoryControllers = new ArrayList<>();
	
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
	public void addCategoryController(CategoryControllerAPI controller){
		categoryControllers.add(controller);
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

	@Override
	public Iterator<? extends CategoryControllerAPI> getCategoryIterator() {
		return categoryControllers.iterator();
	}

}
