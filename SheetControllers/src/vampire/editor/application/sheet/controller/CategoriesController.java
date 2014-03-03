package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vampire.editor.plugin.api.application.sheet.controller.CategoriesControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.CategoryControllerAPI;

public class CategoriesController implements CategoriesControllerAPI{
	
	private final List<CategoryControllerAPI> controllers = new ArrayList<>();

	@Override
	public CategoryControllerAPI get(int i) {
		return controllers.get(i);
	}
	
	public void addCategoryController(CategoryController controller){
		controllers.add(controller);
	}

	@Override
	public Iterator<CategoryControllerAPI> iterator() {
		return controllers.iterator();
	}

}
