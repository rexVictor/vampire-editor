package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import vampire.editor.domain.sheet.Category;
import vampire.editor.domain.sheet.Data;
import vampire.editor.domain.sheet.SubCategory;
import vampire.editor.domain.sheet.Trait;
import vampire.editor.domain.sheet.Value;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.fullapi.sheet.ITrait;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TraitsParser {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	public Value getValue(Object object){
		try{
			String string = mapper.writeValueAsString(object);
			
			Value value = mapper.readValue(string, Value.class);
			return value;
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ITrait getTrait(Object object){
		Map<?, ?> map = (Map<?, ?>) object;
		Trait trait = new Trait((String) map.get("name"), getValue(map.get("value")), null);
		return trait;		
	}
	
	public SubCategory getSubCategory(Object object){
		Map<?,?> subCatMap = (Map<?, ?>) object;
		List<?> traits = (List<?>) subCatMap.get("traits");
		SubCategory subCat = new SubCategory(null);
		subCat.setName((String) subCatMap.get("name")); 
		for (Object o : traits){
			subCat.add(getTrait(o));
		}
		return subCat;
	}
	
	public Category getCategory(Object object){
		Map<?, ?> catMap = (Map<?, ?>) object;
		Category cat = new Category(null);
		cat.setName((String) catMap.get("name"));
		List<?> list= (List<?>) catMap.get("subCats");
		for (Object o : list){
			cat.add(getSubCategory(o));
		}
		return cat;
	}
	
	public Data<CategoryAPI, Category> getCategories(Object object){
		List<?> categories = (List<?>) object;
		Data<CategoryAPI, Category> data = new Data<>(null);
		for (Object o : categories){
			data.add(getCategory(o));
		}
		return data;
	}
	
	

}
