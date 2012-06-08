package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.List;

import vampire.editor.domain.sheet.Sheet;
import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.view.sheet.SheetView;


public class SheetController implements SheetControllerAPI {
	
	private final Sheet sheet;
	
	private final SheetView view;
	
	private final List<CategoryControllerAPI> categoryControllers = new ArrayList<>();

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
	
	
	
	
	
	

}
