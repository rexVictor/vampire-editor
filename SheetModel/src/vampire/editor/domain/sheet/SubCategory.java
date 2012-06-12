package vampire.editor.domain.sheet;

import vampire.editor.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;


public class SubCategory extends Data<Trait> implements SubCategoryAPI{
	
	public SubCategory(){
		
	}
	
	public SubCategory(SubCategoryViewAttributes attributes) {
		super(attributes);		
	}

	@Override
	public SubCategory clone(){
		return (SubCategory) super.clone();
	}
	
	
	@Override
	public SubCategoryViewAttributes getViewAtts(){
		return (SubCategoryViewAttributes) super.getViewAtts();
	}

}
