package vampire.editor.domain.sheet;

import java.util.HashMap;
import java.util.Map;

import vampire.editor.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.domain.sheet.view.MetaEntryViewAttributes;
import vampire.editor.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.domain.sheet.view.TraitViewAttributes;
import vampire.editor.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.fullapi.sheet.ICategory;
import vampire.editor.plugin.fullapi.sheet.IData;
import vampire.editor.plugin.fullapi.sheet.IMetaEntry;
import vampire.editor.plugin.fullapi.sheet.ISheet;
import vampire.editor.plugin.fullapi.sheet.ISubCategory;
import vampire.editor.plugin.fullapi.sheet.ITrait;
import vampire.editor.plugin.fullapi.sheet.IValue;
import vampire.editor.plugin.fullapi.sheet.SheetConstructors;
import vampire.editor.plugin.fullapi.sheet.view.ICategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.IMetaEntryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;

public class Classes implements SheetConstructors{
	
	private final Map<Class<?>, Class<?>> clazzMap = new HashMap<>();
	
	public Classes(){
		initialize();
	}
	
	private void initialize(){
		clazzMap.put(IMetaEntry.class, MetaEntry.class);
		clazzMap.put(ITrait.class, Trait.class);
		clazzMap.put(IValue.class, Value.class);
		clazzMap.put(IData.class, Data.class);
		clazzMap.put(ISubCategory.class, SubCategory.class);
		clazzMap.put(ICategory.class, Category.class);
		clazzMap.put(ISheet.class, Sheet.class);
		clazzMap.put(IValueViewAttributes.class, ValueViewAttributes.class);
		clazzMap.put(ITraitViewAttributes.class, TraitViewAttributes.class);
		clazzMap.put(ISubCategoryViewAttributes.class, SubCategoryViewAttributes.class);
		clazzMap.put(ICategoryViewAttributes.class, CategoryViewAttributes.class);
		clazzMap.put(IMetaEntryViewAttributes.class, MetaEntryViewAttributes.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Class<? extends T> getImplementingClassOf(Class<T> interfaceClass) {
		return (Class<? extends T>) clazzMap.get(interfaceClass);
	}


}
