package domain.sheet;


import plugin.api.domain.sheet.SubCategoryAPI;
import plugin.api.domain.sheet.view.SubCategoryViewAttributes;

public class SubCategory extends Data<Trait> implements SubCategoryAPI{
	
	public SubCategory(SubCategoryViewAttributes attributes) {
		super(attributes);		
	}

	@Override
	public SubCategory clone(){
		return (SubCategory) super.clone();
	}
	
	
	@Override
	public SubCategoryViewAttributes getAttributes(){
		return (SubCategoryViewAttributes) super.getAttributes();
	}

}
