package vampire.editor.domain.sheet.view;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rex.utils.packageiterator.application.PackageIterator;

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
import vampire.editor.plugin.api.domain.sheet.view.ViewAttConstructors;

public class MViewConstructors implements ViewAttConstructors{
	
	private static Map<Class<?>, Class<?>> interfaceToImplementationMap;
	
	static{
		String packageName = MViewConstructors.class.getPackage().getName();
		Map<Class<?>, Class<?>> map = new HashMap<>();
		try {
			List<Class<?>> classes = PackageIterator.getClassesInPackage(packageName);
			for (Class<?> clazz : classes){
				if (clazz.getSimpleName().endsWith("Attributes")){
					Class<?>[] superTypes = clazz.getInterfaces();
					for (Class<?> superType : superTypes){
						if (superType.getSimpleName().endsWith("Attributes")){
							map.put(superType, clazz);
						}
					}
				}
			}
		} catch (ClassNotFoundException | IOException | URISyntaxException e) {
			throw new RuntimeException(e);
		}
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
