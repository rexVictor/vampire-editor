package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;

public interface SubCategoryAPI extends DataAPI<TraitAPI>{

	public SubCategoryAPI clone();

	public SubCategoryViewAttributes getAttributes();

}