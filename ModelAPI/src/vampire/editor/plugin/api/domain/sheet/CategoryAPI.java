package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributesAPI;

public interface CategoryAPI extends PseudoDataAPI<SubCategoryAPI>, Nameable{

	public CategoryAPI clone();

	public CategoryViewAttributesAPI getAttributes();

}