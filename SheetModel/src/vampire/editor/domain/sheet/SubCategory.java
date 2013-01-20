package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;


public class SubCategory extends Data<Trait> implements SubCategoryAPI{
	
	public SubCategory(){
		
	}
	
	@Override
	public SubCategory clone(){
		return (SubCategory) super.clone();
	}

}
