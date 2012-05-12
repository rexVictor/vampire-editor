package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributesAPI;

public interface SubCategoryAPI extends PseudoDataAPI<TraitAPI>{

	@Override
	public SubCategoryAPI clone();

	@Override
	public SubCategoryViewAttributesAPI getViewAtts();

}