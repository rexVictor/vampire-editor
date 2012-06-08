package vampire.editor.sheetloader.application.importer;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.api.plugin.ResourcesHolderAPI;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class Objects<V> {

	private final Map<Integer, V> values = new HashMap<>();
	
	public Objects(Path path, Class<? extends V> clazz, ResourcesHolderAPI resources) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Font.class, new FontDeserializer(resources));
		mapper.registerModule(module);
		
		List<Map<String, Object>> values = mapper.readValue(path.toFile(), new TypeReference<List<Map<String, Object>>>() {
		});
		for (Map<String, Object> o : values){
			int id = (int) o.remove("id");
			V value = mapper.convertValue(o, clazz);
			this.values.put(id, value);
		}	
	}
	
	public V getObjectByID(int id){
		V v = values.get(id);
		if (v instanceof Nameable){
			@SuppressWarnings("unchecked")
			V clone = (V) ((Nameable) v).clone();
			return clone;
		}
		return v;
	}

}
