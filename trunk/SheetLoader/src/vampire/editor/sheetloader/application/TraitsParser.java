package vampire.editor.sheetloader.application;

import java.util.List;
import java.util.Map;


import vampire.editor.domain.sheet.Category;
import vampire.editor.domain.sheet.Data;
import vampire.editor.domain.sheet.SubCategory;
import vampire.editor.domain.sheet.Trait;
import vampire.editor.domain.sheet.Value;
import vampire.editor.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.domain.sheet.view.SubCategoryViewAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TraitsParser {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private final ViewParser viewParser;
	
	public TraitsParser() {
		super();
		viewParser = new ViewParser();		
	}
	
	/**
	 * This method converts an json object to an IValue object, if the conditions met.
	 * It should be unnecessary, since this object is automatically constructed by the framework.
	 * @param object
	 * @return
	 */
	@SuppressWarnings("unused")
	private Value getValue(Object object){
		try{
			Value value = mapper.convertValue(object, Value.class);
			return value;
		}
		catch (Exception e){
			
		}
		return null;
	}
	
	public Trait getTrait(Object object){
		Trait trait = mapper.convertValue(object, Trait.class);
		return trait;		
	}
	
	public SubCategory getSubCategory(Object object) throws InstantiationException, IllegalAccessException{
		Map<?,?> subCatMap = (Map<?, ?>) object;
		List<?> traits = (List<?>) subCatMap.get("traits");
		SubCategory subCat = SubCategory.class.newInstance();
		subCat.setName((String) subCatMap.get("name")); 
		for (Object o : traits){
			subCat.add(getTrait(o));
		}
		SubCategoryViewAttributes viewAtts = viewParser.parseSubCategoryViewAttributes(subCatMap.get("viewAtts"));
		if (viewAtts == null) {
			System.out.println("NULL");
		}
		subCat.setViewAtts(viewAtts);
		return subCat;
	}
	
	public Category getCategory(Object object) throws InstantiationException, IllegalAccessException{
		Map<?, ?> catMap = (Map<?, ?>) object;
		Category cat = Category.class.newInstance();
		cat.setName((String) catMap.get("name"));
		List<?> list= (List<?>) catMap.get("subCats");
		for (Object o : list){
			cat.add(getSubCategory(o));
		}
		CategoryViewAttributes viewAtts = viewParser.parseCategoryViewAttributes(catMap.get("viewAtts"));
		cat.setViewAtts(viewAtts);
		if (viewAtts == null){
			System.out.println("NULL!!!!");
		}
		return cat;
	}
	
	public Data<Category> getCategories(Object object) throws InstantiationException, IllegalAccessException{
		List<?> categories = (List<?>) object;
		@SuppressWarnings("unchecked")
		Data<Category> data = Data.class.newInstance();
		for (Object o : categories){
			data.add(getCategory(o));
		}
		
		return data;
	}
	
	

}
