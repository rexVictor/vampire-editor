package domain.sheet;

import plugin.api.domain.sheet.CategoryAPI;
import plugin.api.domain.sheet.SubCategoryAPI;
import plugin.api.domain.sheet.view.CategoryViewAttributes;

public class Category extends Data<SubCategoryAPI, SubCategory> implements CategoryAPI{
	
	public Category(CategoryViewAttributes attributes) {
		super(attributes);		
	}

	@Override
	public Category clone(){
		return (Category) super.clone();
	}
	
	@Override
	public CategoryViewAttributes getAttributes(){
		return (CategoryViewAttributes) super.getAttributes();
	}

}
