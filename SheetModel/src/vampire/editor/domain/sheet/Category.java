package vampire.editor.domain.sheet;

import vampire.editor.plugin.fullapi.sheet.ICategory;
import vampire.editor.plugin.fullapi.sheet.ISubCategory;
import vampire.editor.plugin.fullapi.sheet.view.ICategoryViewAttributes;

public class Category extends Data<ISubCategory> implements  ICategory{
	
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

	@Override
	public void setViewAtts(ICategoryViewAttributes viewAtts) {
		super.setViewAtts(viewAtts);		
	}

}
