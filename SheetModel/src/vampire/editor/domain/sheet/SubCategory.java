package vampire.editor.domain.sheet;


import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributesAPI;
import vampire.editor.plugin.fullapi.sheet.ISubCategory;
import vampire.editor.plugin.fullapi.sheet.ITrait;

public class SubCategory extends Data<ITrait> implements ISubCategory{
	
	public SubCategory(SubCategoryViewAttributesAPI attributes) {
		super(attributes);		
	}

	@Override
	public SubCategory clone(){
		return (SubCategory) super.clone();
	}
	
	
	@Override
	public SubCategoryViewAttributesAPI getAttributes(){
		return (SubCategoryViewAttributesAPI) super.getAttributes();
	}

}
