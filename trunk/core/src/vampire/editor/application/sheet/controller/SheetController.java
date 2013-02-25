package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vampire.editor.domain.sheet.Sheet;
import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.view.sheet.SheetView;


public class SheetController implements SheetControllerAPI {
	
	private final Sheet sheet;
	
	private final SheetView view;
	
	private final List<CategoryControllerAPI> categoryControllers = new ArrayList<>();
	
	private final List<MetaEntryControllerAPI> metaEntryControllers = new ArrayList<>();
	
	private MiscController miscController;
	
	private final Map<String, MetaEntryControllerAPI> metaEntryControllerMap = new HashMap<>();

	public SheetController(Sheet sheet, SheetView view) {
		super();
		this.sheet = sheet;
		this.view = view;
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

	@Override
	public void addMetaEntryController(MetaEntryControllerAPI controller) {
		metaEntryControllers.add(controller);
		String title = controller.getMetaEntry().getName();
		metaEntryControllerMap.put(title, controller);
	}

	@Override
	public MetaEntryControllerAPI getMetaEntryController(String key) {
		return metaEntryControllerMap.get(key);
	}

	public MiscController getMiscController() {
		return miscController;
	}

	public void setMiscController(MiscController miscController) {
		this.miscController = miscController;
	}
	
	
	
	
	

}
