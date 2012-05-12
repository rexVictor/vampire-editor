package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributesAPI;
import vampire.editor.plugin.fullapi.sheet.ICategory;
import vampire.editor.plugin.fullapi.sheet.ISubCategory;

public class Category extends Data<ISubCategory> implements  ICategory{
	
	public Category(CategoryViewAttributesAPI attributes) {
		super(attributes);		
	}

	@Override
	public Category clone(){
		return (Category) super.clone();
	}
	
	@Override
	public CategoryViewAttributesAPI getAttributes(){
		return (CategoryViewAttributesAPI) super.getAttributes();
	}

}
