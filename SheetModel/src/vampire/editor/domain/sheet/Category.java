package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.fullapi.sheet.view.ICategoryViewAttributes;

public class Category extends Data<SubCategory> implements  CategoryAPI{
	
	public Category(){
		
	}
	
	public Category(ICategoryViewAttributes attributes) {
		super(attributes);		
	}

	@Override
	public Category clone(){
		return (Category) super.clone();
	}
	
	@Override
	public ICategoryViewAttributes getViewAtts(){
		return (ICategoryViewAttributes) super.getViewAtts();
	}

	public void setViewAtts(ICategoryViewAttributes viewAtts) {
		super.setViewAtts(viewAtts);		
	}

}
