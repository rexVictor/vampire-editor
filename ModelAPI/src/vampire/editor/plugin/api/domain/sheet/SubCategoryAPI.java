package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributesAPI;

public interface SubCategoryAPI extends PseudoDataAPI<TraitAPI>{

	public SubCategoryAPI clone();

	public SubCategoryViewAttributesAPI getAttributes();

}