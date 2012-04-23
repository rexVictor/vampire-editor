package vampire.editor.domain.sheet;


import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.ISubCategory;
import vampire.editor.plugin.fullapi.sheet.ITrait;

public class SubCategory extends Data<TraitAPI, ITrait> implements ISubCategory{
	
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
