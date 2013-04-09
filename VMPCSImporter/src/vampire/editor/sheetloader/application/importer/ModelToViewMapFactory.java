package vampire.editor.sheetloader.application.importer;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import vampire.editor.sheetloader.common.ModelToViewMap;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Deprecated
public class ModelToViewMapFactory {
	
	@SuppressWarnings("unchecked")
	public static ModelToViewMap loadModelToViewMap(Path path) throws JsonParseException, JsonMappingException, IOException{
		ModelToViewMap modelToViewMap = new ModelToViewMap();
		ObjectMapper mapper = new ObjectMapper();
		Map<String, ?> protoMap = mapper.readValue(path.toFile(), new TypeReference<Map<String, ?>>() {});

		List<Map<String, Integer>> metas = (List<Map<String, Integer>>) protoMap.remove("meta");
		for (Map<String, Integer> map : metas){
			int model 	= map.remove("model");
			int view	= map.remove("viewatts");
			modelToViewMap.putMetaPair(model, view);
		}
		
		List<Map<String, Integer>> categories = (List<Map<String, Integer>>) protoMap.remove("categories");
		for (Map<String, Integer> map : categories){
			int model 	= map.remove("model");
			int view	= map.remove("viewatts");
			modelToViewMap.putCategoryPair(model, view);
		}
		
		List<Map<String, Integer>> subcategories = (List<Map<String, Integer>>) protoMap.remove("subcategories");
		for (Map<String, Integer> map : subcategories){
			int model 	= map.remove("model");
			int view	= map.remove("viewatts");
			modelToViewMap.putSubcategoryPair(model, view);
		}
		
		List<Map<String, Integer>> traits = (List<Map<String, Integer>>) protoMap.remove("traits");
		for (Map<String, Integer> map : traits){
			int model 	= map.remove("model");
			int view	= map.remove("viewatts");
			modelToViewMap.putTraitPair(model, view);
		}
		
		List<Map<String, Integer>> values = (List<Map<String, Integer>>) protoMap.remove("values");
		for (Map<String, Integer> map : values){
			int model 	= map.remove("model");
			int view	= map.remove("viewatts");
			modelToViewMap.putValuePair(model, view);
		}
		
		Map<String, Integer> bloodPool = (Map<String, Integer>) protoMap.remove("bloodpool");
		modelToViewMap.putBloodPoolPair(bloodPool.remove("model"), bloodPool.remove("viewatts"));
		
		List<Map<String, Integer>> healthEntries = (List<Map<String, Integer>>) protoMap.remove("healthentries");
		for (Map<String, Integer> map : healthEntries){
			int model 	= map.remove("model");
			int view	= map.remove("viewatts");
			modelToViewMap.putHealthEntryPair(model, view);
		}
		
		Map<String, Integer> health = (Map<String, Integer>) protoMap.remove("health");
		modelToViewMap.putHealthPair(health.remove("model"), health.remove("viewatts"));
		
		List<Map<String, Integer>> merits = (List<Map<String, Integer>>) protoMap.remove("merits");
		for (Map<String, Integer> map : merits){
			modelToViewMap.putMeritPair(map.remove("model"), map.remove("viewatts"));
		}
		
		
		List<Map<String, Integer>> meritEntries = (List<Map<String, Integer>>) protoMap.remove("meritentries");
		for (Map<String, Integer> map : meritEntries){
			int model 	= map.remove("model");
			int view	= map.remove("viewatts");
			modelToViewMap.putMeritEntryPair(model, view);
		}
		
		return modelToViewMap;
	}

}
