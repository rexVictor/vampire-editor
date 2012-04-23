package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.ICategory;
import vampire.editor.plugin.fullapi.sheet.ISubCategory;

public class Category extends Data<SubCategoryAPI, ISubCategory> implements  ICategory{
	
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
