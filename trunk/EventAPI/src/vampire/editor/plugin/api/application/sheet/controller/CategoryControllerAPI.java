package vampire.editor.plugin.api.application.sheet.controller;

import java.util.Iterator;

import vampire.editor.plugin.api.application.sheet.events.CategoryListener;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.view.sheet.CategoryView;

public interface CategoryControllerAPI {

	public void addSubCategory(SubCategoryControllerAPI traitController);

	public void removeTrait(SubCategoryControllerAPI traitController);

	public void insertTrait(int index, SubCategoryControllerAPI controller);

	public void addListener(CategoryListener listener);

	public void removeListener(CategoryListener listener);

	public CategoryAPI getSubCategory();

	public CategoryView getView();
	
	public Iterator<? extends SubCategoryControllerAPI> getSubCategoryControllerIterator();

}