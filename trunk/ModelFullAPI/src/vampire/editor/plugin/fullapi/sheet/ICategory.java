package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributes;

public interface ICategory extends CategoryAPI, IData<SubCategoryAPI, ISubCategory>{

	public ICategory clone();

	public CategoryViewAttributes getAttributes();

}