package vampire.editor.sheetloader.common;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vampire.editor.domain.sheet.Category;
import vampire.editor.domain.sheet.MetaEntry;
import vampire.editor.domain.sheet.SubCategory;
import vampire.editor.domain.sheet.Trait;
import vampire.editor.domain.sheet.Value;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ModelToViewMap {

	private static final TypeReference<Map<String, List<Map<String, Integer>>>> TYPE_REFERENCE =
			new TypeReference<Map<String,List<Map<String,Integer>>>>() {};
	
	private final Map<Integer, Integer> metaMap = new HashMap<>();
	
	private final Map<Integer, Integer> categoryMap = new HashMap<>();
	
	private final Map<Integer, Integer> subcategoryMap = new HashMap<>();
	
	private final Map<Integer, Integer> traitMap = new HashMap<>();
	
	private final Map<Integer, Integer> valueMap = new HashMap<>();
	
	private final Map<Class<?>, Map<Integer, Integer>> allMaps = new HashMap<>();
	
	public ModelToViewMap(){
		allMaps.put(MetaEntry.class, metaMap);
		allMaps.put(Category.class, categoryMap);
		allMaps.put(SubCategory.class, subcategoryMap);
		allMaps.put(Trait.class, traitMap);
		allMaps.put(Value.class, valueMap);
	}
	
	public void putMetaPair(int model, int view){
		metaMap.put(model, view);
	}
	
	public int getMetaView(int model){
		return metaMap.get(model);
	}
	
	public void putCategoryPair(int model, int view){
		categoryMap.put(model, view);
	}
	
	public int getCategoryView(int model){
		return categoryMap.get(model);
	}
	
	public void putSubcategoryPair(int model, int view){
		subcategoryMap.put(model, view);
	}
	
	public int getSubcategoryView(int model){
		return subcategoryMap.get(model);
	}
	
	public void putTraitPair(int model, int view){
		traitMap.put(model, view);
	}
	
	public int getTraitView(int model){
		return traitMap.get(model);
	}
	
	public void putValuePair(int model, int view){
		valueMap.put(model, view);
	}
	
	public int getValueView(int model){
		return valueMap.get(model);
	}
	
	public void putPair(Class<?> clazz, int model, int view){
		allMaps.get(clazz).put(model, view);
	}
	
	@Override
	public String toString(){
		return metaMap.toString();
	}
	
	public Map<String, Object> toMap(){
		Map<String, Object> map = new HashMap<>();
		map.put("meta", makeMap(metaMap));
		map.put("subcategories", makeMap(subcategoryMap));
		map.put("categories", makeMap(categoryMap));
		map.put("traits", makeMap(traitMap));
		map.put("values", makeMap(valueMap));
		return map;
	}
	
	private List<Map<String, Object>> makeMap(Map<Integer, Integer> map){
		List<Map<String, Object>> list = new LinkedList<>();
		Set<Integer> models = map.keySet();
		for (int i : models){
			Map<String, Object> pair = new HashMap<>();
			pair.put("model", i);
			pair.put("viewatts", map.get(i));
			list.add(pair);
		}
		return list;
	}
	
	public static ModelToViewMap loadModelToViewMap(Path path) throws JsonParseException, JsonMappingException, IOException{
		ModelToViewMap modelToViewMap = new ModelToViewMap();
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, List<Map<String, Integer>>> protoMap = mapper.readValue(path.toFile(), TYPE_REFERENCE);
		List<Map<String, Integer>> metas = protoMap.remove("meta");
		for (Map<String, Integer> map : metas){
			int model 	= map.remove("model");
			int view	= map.remove("viewatts");
			modelToViewMap.putMetaPair(model, view);
		}
		
		List<Map<String, Integer>> categories = protoMap.remove("categories");
		for (Map<String, Integer> map : categories){
			int model 	= map.remove("model");
			int view	= map.remove("viewatts");
			modelToViewMap.putCategoryPair(model, view);
		}
		
		List<Map<String, Integer>> subcategories = protoMap.remove("subcategories");
		for (Map<String, Integer> map : subcategories){
			int model 	= map.remove("model");
			int view	= map.remove("viewatts");
			modelToViewMap.putSubcategoryPair(model, view);
		}
		
		List<Map<String, Integer>> traits = protoMap.remove("traits");
		for (Map<String, Integer> map : traits){
			int model 	= map.remove("model");
			int view	= map.remove("viewatts");
			modelToViewMap.putTraitPair(model, view);
		}
		
		List<Map<String, Integer>> values = protoMap.remove("values");
		for (Map<String, Integer> map : values){
			int model 	= map.remove("model");
			int view	= map.remove("viewatts");
			modelToViewMap.putValuePair(model, view);
		}
		
		
		return modelToViewMap;
	}
}
