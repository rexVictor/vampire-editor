package vampire.editor.domain.sheet;

import vampire.editor.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;

public class Category extends Data<SubCategory> implements  CategoryAPI{
	
	public Category(){
		
	}
	
	public Category(CategoryViewAttributes attributes) {
		super(attributes);		
	}

	@Override
	public Category clone(){
		return (Category) super.clone();
	}
	
	@Override
	public CategoryViewAttributes getViewAtts(){
		return (CategoryViewAttributes) super.getViewAtts();
	}

	public void setViewAtts(CategoryViewAttributes viewAtts) {
		super.setViewAtts(viewAtts);		
	}

}
