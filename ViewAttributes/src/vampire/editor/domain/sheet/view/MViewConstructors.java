package vampire.editor.domain.sheet.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import vampire.editor.plugin.api.domain.sheet.view.BloodPoolViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.HealthEntryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.HealthViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.MeritViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.VersionViewAttributesImplementation;
import vampire.editor.plugin.api.domain.sheet.view.ViewAttConstructors;

@VersionViewAttributesImplementation(version = "1.0.0.0",
	compatibleWithSpecifications = "1.0.0.0", name = "Default ViewAttsImpl 1.0.0.0")
public class MViewConstructors implements ViewAttConstructors{
	
	private static Map<Class<?>, Class<?>> interfaceToImplementationMap;
	
	static{
		Map<Class<?>, Class<?>> map = new HashMap<>();
		map.put(BloodPoolViewAttributes.class, MBloodPoolViewAttributes.class);
		interfaceToImplementationMap = Collections.unmodifiableMap(map);
	}

	@Override
	public BloodPoolViewAttributes createBloodPoolViewAttributes() {
		return new MBloodPoolViewAttributes();
	}

	@Override
	public CategoryViewAttributes createCategoryViewAttributes() {
		return new MCategoryViewAttributes();
	}

	@Override
	public HealthEntryViewAttributes createHealthEntryViewAttributes() {
		return new MHealthEntryViewAttributes();
	}

	@Override
	public MeritEntryViewAttributes createMeritEntryViewAttributes() {
		return new MMeritEntryViewAttributes();
	}

	@Override
	public HealthViewAttributes createHealthViewAttributes() {
		return new MHealthViewAttributes();
	}

	@Override
	public MetaEntryViewAttributes createMetaEntryViewAttributes() {
		return new MMetaEntryViewAttributes();
	}

	@Override
	public SubCategoryViewAttributes createSubCategoryViewAttributes() {
		return new MSubCategoryViewAttributes();
	}

	@Override
	public TraitViewAttributes createTraitViewAttributes() {
		return new MTraitViewAttributes();
	}

	@Override
	public ValueViewAttributes createValueViewAttributes() {
		return new MValueViewAttributes();
	}

	@Override
	public Map<Class<?>, Class<?>> getInterfaceToImplementationMap() {
		return interfaceToImplementationMap;
	}

	@Override
	public MeritViewAttributes createMeritViewAttributes() {
		return new MMeritViewAttributes();
	}

}
