package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributes;

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
