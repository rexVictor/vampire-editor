package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.plugin.fullapi.sheet.IValue;

public class ValueHolder {
	
	private final Map<Integer, IValue> valueMap = new HashMap<>();
	
	private final Path path;
	
	private final Class<? extends IValue> implementingClass;
	
	public ValueHolder(Path path, Class<? extends IValue> implementingClass) throws JsonParseException, JsonMappingException, IOException {
		super();		
		this.path = path;
		this.implementingClass = implementingClass;
		load();
	}
	
	private void load() throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, ?> json = mapper.readValue(path.toFile(), Map.class);
		@SuppressWarnings("unchecked")
		List<Map<String, ?>> values = (List<Map<String, ?>>) json.get("values");
		for (Map<String, ?> valueMap : values){
			int id = (Integer) valueMap.remove("id");
			IValue value = mapper.convertValue(valueMap, implementingClass);
			this.valueMap.put(id, value);
		}
	}
	
	@Override
	public String toString(){
		return valueMap.toString();
	}
	
	public IValue getValueByID(int id){
		return valueMap.get(id).clone();
	}
	
	

}
