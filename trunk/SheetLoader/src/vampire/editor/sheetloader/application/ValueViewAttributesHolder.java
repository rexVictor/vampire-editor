package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.domain.sheet.view.ValueViewAttributes;

public class ValueViewAttributesHolder {
	
	private final Path path;
	
	private final Map<Integer, ValueViewAttributes> valueViewAttributeMap = new HashMap<>();

	public ValueViewAttributesHolder(Path path) throws JsonParseException, JsonMappingException, IOException {
		super();
		this.path = path;
		load();
	}
	
	private void load() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		Map<?, ?> map = mapper.readValue(path.toFile(), Map.class);
		@SuppressWarnings("unchecked")
		List<Map<String, ?>> viewAtts = (List<Map<String, ?>>) map.get("valueViews");
		for (Map<String, ?> viewAttMap : viewAtts){
			int id = (Integer) viewAttMap.remove("id");
			ValueViewAttributes viewAtt = mapper.convertValue(viewAttMap, ValueViewAttributes.class);
			valueViewAttributeMap.put(id, viewAtt);
		}
	}
	
	@Override
	public String toString(){
		return valueViewAttributeMap.toString();
	}
	
	public ValueViewAttributes getValueViewAttributesById(int id){
		return valueViewAttributeMap.get(id).clone();
	}
	
	
	
	

}
