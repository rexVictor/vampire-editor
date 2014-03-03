package vampire.editor.plugin.api.domain.sheet.view;

import java.util.Map;

@VersionViewAttributesSpecification(version = "1.0.0.0", compatibleVersions = "1.0.0.0")
public interface ViewAttConstructors {
	
	public BloodPoolViewAttributes createBloodPoolViewAttributes();
	
	public CategoryViewAttributes createCategoryViewAttributes();
	
	public HealthEntryViewAttributes createHealthEntryViewAttributes();
	
	public MeritEntryViewAttributes createMeritEntryViewAttributes();
	
	public HealthViewAttributes createHealthViewAttributes();
	
	public MetaEntryViewAttributes createMetaEntryViewAttributes();
	
	public SubCategoryViewAttributes createSubCategoryViewAttributes();
	
	public TraitViewAttributes createTraitViewAttributes();
	
	public ValueViewAttributes createValueViewAttributes();
	
	public Map<Class<?>, Class<?>> getInterfaceToImplementationMap();
	
	public MeritViewAttributes createMeritViewAttributes();

}
