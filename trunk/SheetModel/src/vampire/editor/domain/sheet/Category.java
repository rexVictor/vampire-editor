package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;

public class Category extends Data<SubCategory> implements  CategoryAPI{
	
	public Category(){
		
	}
	
	@Override
	public Category clone(){
		return (Category) super.clone();
	}
	
}
