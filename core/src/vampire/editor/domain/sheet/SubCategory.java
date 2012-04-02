package vampire.editor.domain.sheet;


import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;

public class SubCategory extends Data<TraitAPI, Trait> implements SubCategoryAPI{
	
	public SubCategory(SubCategoryViewAttributes attributes) {
		super(attributes);		
	}

	@Override
	public SubCategory clone(){
		return (SubCategory) super.clone();
	}
	
	
	@Override
	public SubCategoryViewAttributes getAttributes(){
		return (SubCategoryViewAttributes) super.getAttributes();
	}

}