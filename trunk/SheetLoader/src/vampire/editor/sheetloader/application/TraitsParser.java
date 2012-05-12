package vampire.editor.sheetloader.application;

import java.util.List;
import java.util.Map;


import vampire.editor.plugin.fullapi.sheet.ICategory;
import vampire.editor.plugin.fullapi.sheet.IData;
import vampire.editor.plugin.fullapi.sheet.ISubCategory;
import vampire.editor.plugin.fullapi.sheet.ITrait;
import vampire.editor.plugin.fullapi.sheet.IValue;
import vampire.editor.plugin.fullapi.sheet.SheetConstructors;
import vampire.editor.plugin.fullapi.sheet.view.ICategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class TraitsParser {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private final SheetConstructors constructors;
	
	private final ViewParser viewParser;
	
	public TraitsParser(SheetConstructors constructors) {
		super();
		this.constructors = constructors;
		viewParser = new ViewParser(constructors);
		SimpleModule module = new SimpleModule();
		module.addAbstractTypeMapping(ITrait.class, constructors.getImplementingClassOf(ITrait.class));
		module.addAbstractTypeMapping(IValue.class, constructors.getImplementingClassOf(IValue.class));
		module.addAbstractTypeMapping(IValueViewAttributes.class, constructors.getImplementingClassOf(IValueViewAttributes.class));
		module.addAbstractTypeMapping(ITraitViewAttributes.class, constructors.getImplementingClassOf(ITraitViewAttributes.class));
		module.addAbstractTypeMapping(ISubCategory.class, constructors.getImplementingClassOf(ISubCategory.class));
		module.addAbstractTypeMapping(ICategory.class, constructors.getImplementingClassOf(ICategory.class));
		mapper.registerModule(module);
	}
	
	/**
	 * This method converts an json object to an IValue object, if the conditions met.
	 * It should be unnecessary, since this object is automatically constructed by the framework.
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unused")
	private IValue getValue(Object object){
		try{
			IValue value = mapper.convertValue(object, IValue.class);
			return value;
		}
		catch (Exception e){
			
		}
		return null;
	}
	
	public ITrait getTrait(Object object){
		ITrait trait = mapper.convertValue(object, ITrait.class);
		return trait;		
	}
	
	public ISubCategory getSubCategory(Object object) throws InstantiationException, IllegalAccessException{
		Map<?,?> subCatMap = (Map<?, ?>) object;
		List<?> traits = (List<?>) subCatMap.get("traits");
		ISubCategory subCat = constructors.getImplementingClassOf(ISubCategory.class).newInstance();
		subCat.setName((String) subCatMap.get("name")); 
		for (Object o : traits){
			subCat.add(getTrait(o));
		}
		ISubCategoryViewAttributes viewAtts = viewParser.parseSubCategoryViewAttributes(subCatMap.get("viewAtts"));
		subCat.setViewAtts(viewAtts);
		return subCat;
	}
	
	public ICategory getCategory(Object object) throws InstantiationException, IllegalAccessException{
		Map<?, ?> catMap = (Map<?, ?>) object;
		ICategory cat = constructors.getImplementingClassOf(ICategory.class).newInstance();
		cat.setName((String) catMap.get("name"));
		List<?> list= (List<?>) catMap.get("subCats");
		for (Object o : list){
			cat.add(getSubCategory(o));
		}
		ICategoryViewAttributes viewAtts = viewParser.parseCategoryViewAttributes(catMap.get("viewAtts"));
		cat.setViewAtts(viewAtts);
		if (viewAtts == null){
			System.out.println("NULL!!!!");
		}
		return cat;
	}
	
	public IData<ICategory> getCategories(Object object) throws InstantiationException, IllegalAccessException{
		List<?> categories = (List<?>) object;
		@SuppressWarnings("unchecked")
		IData<ICategory> data = constructors.getImplementingClassOf(IData.class).newInstance();
		for (Object o : categories){
			data.add(getCategory(o));
		}
		
		return data;
	}
	
	

}
