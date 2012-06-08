package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;

import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;

public class SubCategory extends Data<Trait> implements SubCategoryAPI{
	
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
