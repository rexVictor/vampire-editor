package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.*;

public interface ModelToViewModelMapperAPI {

	public CategoryViewAttributesAPI getViewAttributes(CategoryAPI category);
	
	public SubCategoryViewAttributesAPI getViewAttributes(SubCategoryAPI subcategory);
	
	public TraitViewAttributesAPI getViewAttributes(TraitAPI trait);
	
	public ValueViewAttributesAPI getViewAttributes(ValueAPI value);
	
	public MetaEntryViewAttributesAPI getViewAttributes(MetaEntryAPI metaEntry);
	
	public HealthEntryViewAttributesAPI getViewAttributes(HealthEntryAPI healthEntry);
	
	public HealthViewAttibutesAPI getViewAttributes(HealthAPI health);
	
	public BloodPoolViewAttributesAPI getViewAttributes(BloodPoolAPI bloodPool);
	
	public MeritEntryViewAttibutesAPI getViewAttributes(MeritAPI merit);
	
	public MeritViewAttributesAPI getViewAttributes(MeritsAPI merits);
	
	public void putView(ValueAPI value, ValueViewAttributesAPI viewAtts);
	
	public void putView(TraitAPI trait, TraitViewAttributesAPI viewAtts);
	
	public void putView(MeritAPI merit, MeritEntryViewAttibutesAPI viewAtts);
	
	public void removeView(ValueAPI value);
	
	public void removeView(TraitAPI trait);
	
	public void removeView(MeritAPI merit);
	
}
