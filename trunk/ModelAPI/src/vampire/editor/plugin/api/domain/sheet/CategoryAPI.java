package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributesAPI;

public interface CategoryAPI extends PseudoDataAPI<SubCategoryAPI>, Nameable{

	@Override
	public CategoryAPI clone();

	@Override
	public CategoryViewAttributesAPI getViewAtts();

}