package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributesAPI;
import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributesAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributesAPI;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributesAPI;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;

public interface ModelToViewModelMapperAPI {

	public CategoryViewAttributesAPI getViewAttributes(CategoryAPI category);
	
	public SubCategoryViewAttributesAPI getViewAttributes(SubCategoryAPI subcategory);
	
	public TraitViewAttributesAPI getViewAttributes(TraitAPI trait);
	
	public ValueViewAttributesAPI getViewAttributes(ValueAPI value);
	
	public MetaEntryViewAttributesAPI getViewAttributes(MetaEntryAPI metaEntry);
	
	

}
