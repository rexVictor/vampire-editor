package vampire.editor.domain.sheet;

import vampire.editor.plugin.fullapi.sheet.ISubCategory;
import vampire.editor.plugin.fullapi.sheet.ITrait;
import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;

public class SubCategory extends Data<ITrait> implements ISubCategory{
	
	public SubCategory(){
		
	}
	
	public SubCategory(ISubCategoryViewAttributes attributes) {
		super(attributes);		
	}

	@Override
	public SubCategory clone(){
		return (SubCategory) super.clone();
	}
	
	
	@Override
	public ISubCategoryViewAttributes getViewAtts(){
		return (ISubCategoryViewAttributes) super.getViewAtts();
	}

}
