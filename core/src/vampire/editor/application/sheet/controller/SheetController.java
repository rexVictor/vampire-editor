package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.List;

import vampire.editor.plugin.api.view.sheet.SheetView;
import vampire.editor.plugin.fullapi.sheet.ISheet;


public class SheetController {
	
	private final ISheet sheet;
	
	private final SheetView view;
	
	private final List<CategoryController> categoryControllers = new ArrayList<>();

	public SheetController(ISheet sheet, SheetView view) {
		super();
		this.sheet = sheet;
		this.view = view;
	}
	
	public void addCategoryController(CategoryController controller){
		categoryControllers.add(controller);
	}
	
	
	
	
	
	

}
