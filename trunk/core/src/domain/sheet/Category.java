package domain.sheet;

import plugin.api.domain.sheet.view.CategoryViewAttributes;

public class Category extends Data<SubCategory>{
	
	public Category(CategoryViewAttributes attributes) {
		super(attributes);		
	}

	public Category clone(){
		return (Category) super.clone();
	}
	
	@Override
	public CategoryViewAttributes getAttributes(){
		return (CategoryViewAttributes) super.getAttributes();
	}

}
