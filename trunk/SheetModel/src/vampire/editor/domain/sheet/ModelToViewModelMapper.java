package vampire.editor.domain.sheet;

import java.util.IdentityHashMap;
import java.util.Map;

import vampire.editor.domain.sheet.view.*;
import vampire.editor.plugin.api.domain.sheet.*;
import vampire.editor.plugin.api.domain.sheet.view.*;

public class ModelToViewModelMapper implements ModelToViewModelMapperAPI{
	
	private Map<Category, CategoryViewAttributes> catViewAtts = new IdentityHashMap<>();
	
	private Map<SubCategory, SubCategoryViewAttributes> subcatViewAtts = new IdentityHashMap<>();
	
	private Map<Trait, TraitViewAttributes> traitViewAtts = new IdentityHashMap<>();
	
	private Map<Value, ValueViewAttributes> valueViewAtts = new IdentityHashMap<>();
	
	private Map<MetaEntry, MetaEntryViewAttributes> metaEntryViewAtts = new IdentityHashMap<>();

	@Override
	public CategoryViewAttributesAPI getViewAttributes(
			CategoryAPI category){ 
		return catViewAtts.get(category);
	}

	@Override
	public SubCategoryViewAttributesAPI getViewAttributes(SubCategoryAPI subcategory) {
		return subcatViewAtts.get(subcategory);
	}

	@Override
	public TraitViewAttributesAPI getViewAttributes(TraitAPI trait) {
		return traitViewAtts.get(trait);
	}

	@Override
	public ValueViewAttributesAPI getViewAttributes(ValueAPI value) {
		return valueViewAtts.get(value);
	}
	
	public void putView(Category category, CategoryViewAttributes viewAtts){
		catViewAtts.put(category, viewAtts);
	}
	
	public void putView(SubCategory subcategory, SubCategoryViewAttributes viewAtts){
		subcatViewAtts.put(subcategory, viewAtts);
	}
	
	public void putView(Trait trait, TraitViewAttributes viewAtts){
		traitViewAtts.put(trait, viewAtts);
	}
	
	public void putView(Value value, ValueViewAttributes viewAtts){
		valueViewAtts.put(value, viewAtts);
	}
	
	public void putView(MetaEntry metaEntry, MetaEntryViewAttributes viewAtts){
		metaEntryViewAtts.put(metaEntry, viewAtts);
	}

	@Override
	public MetaEntryViewAttributesAPI getViewAttributes(MetaEntryAPI metaEntry) {
		return metaEntryViewAtts.get(metaEntry);
	}

}
