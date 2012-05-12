package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributesAPI;

public interface ISubCategory extends SubCategoryAPI, IData<ITrait>{

	public ISubCategory clone();

	public SubCategoryViewAttributesAPI getAttributes();

}