package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;

public interface ISubCategory extends SubCategoryAPI, IData<TraitAPI, ITrait>{

	public ISubCategory clone();

	public SubCategoryViewAttributes getAttributes();

}