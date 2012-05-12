package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;

public interface ISubCategory extends SubCategoryAPI, IData<ITrait>{

	@Override
	public ISubCategory clone();

	@Override
	public ISubCategoryViewAttributes getViewAtts();

}