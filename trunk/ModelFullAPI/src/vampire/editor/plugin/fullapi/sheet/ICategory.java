package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributesAPI;

public interface ICategory extends CategoryAPI, IData<ISubCategory>{

	public ICategory clone();

	public CategoryViewAttributesAPI getAttributes();

}