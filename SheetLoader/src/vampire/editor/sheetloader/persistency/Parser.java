package vampire.editor.sheetloader.persistency;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import vampire.editor.sheetloader.domain.ProtoValueViewAttribute;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Parser {
	
	public Parser(){
		
	}
	
	public void parse(Path path) throws JsonParseException, JsonMappingException, IOException{
		
		Path views = path.resolve("valueViews.json");
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, List<Map<String, Object>>> valueViewsJSON = mapper.readValue(views.toFile(), Map.class);
		System.out.println(valueViewsJSON);
		List<Map<String,Object>> list = valueViewsJSON.get("valueViews");
		for (Map<String,Object> entry : list){
			System.out.println(getViewAtt(entry));
		}
		
	}
	
	private ProtoValueViewAttribute getViewAtt(Map<String, Object> obj){
		Map<String, Object> map = (Map<String, Object>) obj;
		ProtoValueViewAttribute att = new ProtoValueViewAttribute((Boolean) map.get("showSpace"), (Boolean) map.get("dynamic"), (int) map.get("circles"));
		return att;
		
	}
	
	

}
