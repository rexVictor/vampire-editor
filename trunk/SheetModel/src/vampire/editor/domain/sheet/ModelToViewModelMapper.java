package vampire.editor.domain.sheet;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

import vampire.editor.domain.sheet.view.*;
import vampire.editor.plugin.api.domain.sheet.*;
import vampire.editor.plugin.api.domain.sheet.view.*;

public class ModelToViewModelMapper implements ModelToViewModelMapperAPI{
	
	private final Map<Category, CategoryViewAttributes> catViewAtts = new IdentityHashMap<>();
	
	private final Map<SubCategory, SubCategoryViewAttributes> subcatViewAtts = new IdentityHashMap<>();
	
	private final Map<Trait, TraitViewAttributes> traitViewAtts = new IdentityHashMap<>();
	
	private final Map<Value, ValueViewAttributes> valueViewAtts = new IdentityHashMap<>();
	
	private final Map<MetaEntry, MetaEntryViewAttributes> metaEntryViewAtts = new IdentityHashMap<>();
	
	private final Map<BloodPool, BloodPoolViewAttributes> bloodPoolViewAtts = new IdentityHashMap<>();
	
	private final Map<HealthEntry, HealthEntryViewAttributes> healthEntryViewAtts = new IdentityHashMap<>();
	
	private final Map<Health, HealthViewAttributes> healthViewAtts = new IdentityHashMap<>();
	
	private final Map<Merit, MeritEntryViewAttibutes> meritEntryViewAtts = new IdentityHashMap<>();
	
	private final Map<Merits, MeritViewAttributes> meritViewAtts = new IdentityHashMap<>();
	
	private final Map<Class<?>, Map<?,?>> map = new HashMap<>();
	
	public ModelToViewModelMapper(){
		map.put(Category.class, catViewAtts);
		map.put(SubCategory.class, subcatViewAtts);
		map.put(Trait.class, traitViewAtts);
		map.put(Value.class, valueViewAtts);
		map.put(MetaEntry.class, metaEntryViewAtts);
		map.put(BloodPool.class, bloodPoolViewAtts);
		map.put(HealthEntry.class, healthEntryViewAtts);
		map.put(Health.class, healthViewAtts);
		map.put(Merit.class, meritEntryViewAtts);
		map.put(Merits.class, meritViewAtts);
	}
	
	public HealthEntryViewAttributes getViewAttributes(HealthEntryAPI healthEntryAPI){
		return healthEntryViewAtts.get(healthEntryAPI);
	}
	
	public HealthViewAttibutesAPI getViewAttributes(Health health){
		return healthViewAtts.get(health);
	}
	
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
	
	public void putView(BloodPool bloodPool, BloodPoolViewAttributes viewAtts){
		bloodPoolViewAtts.put(bloodPool, viewAtts);
	}
	
	public void putView(HealthEntry healthEntry, HealthEntryViewAttributes viewAtts){
		healthEntryViewAtts.put(healthEntry, viewAtts);
	}
	
	public void putView(Health health, HealthViewAttributes viewAtts){
		healthViewAtts.put(health, viewAtts);
	}
	
	public BloodPoolViewAttributes getViewAttributes(BloodPoolAPI bloodPool){
		return bloodPoolViewAtts.get(bloodPool);
	}

	@Override
	public MetaEntryViewAttributes getViewAttributes(MetaEntryAPI metaEntry) {
		return metaEntryViewAtts.get(metaEntry);
	}
	
	public Object get(Object object){
		Map<?, ?> map = this.map.get(object.getClass());
		if (map != null)
			return map.get(object);
		else
			return null;
	}

}
