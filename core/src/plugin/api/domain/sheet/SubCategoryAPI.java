package plugin.api.domain.sheet;

import plugin.api.domain.sheet.view.SubCategoryViewAttributes;

public interface SubCategoryAPI extends DataAPI<TraitAPI>{

	public SubCategoryAPI clone();

	public SubCategoryViewAttributes getAttributes();

}