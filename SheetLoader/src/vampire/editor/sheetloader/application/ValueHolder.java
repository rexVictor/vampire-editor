package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.domain.sheet.Value;

public class ValueHolder {
	
	private final Map<Integer, Value> valueMap = new HashMap<>();
	
	private final Path path;
	
	public ValueHolder(Path path) throws JsonParseException, JsonMappingException, IOException {
		super();		
		this.path = path;
		
		load();
	}
	
	private void load() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
	
		@SuppressWarnings("unchecked")
		List<Map<String, ?>> values = mapper.readValue(path.toFile(), List.class);
		for (Map<String, ?> valueMap : values){
			int id = (Integer) valueMap.remove("id");
			Value value = mapper.convertValue(valueMap, Value.class);
			this.valueMap.put(id, value);
		}
	}
	
	@Override
	public String toString(){
		return valueMap.toString();
	}
	
	public Value getValueByID(int id){
		return valueMap.get(id).clone();
	}
	
	

}
