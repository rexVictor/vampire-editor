package vampire.editor.sheetloader.application.importer;

import java.awt.Font;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vampire.editor.plugin.api.plugin.ResourcesHolderAPI;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class Objects<V> {

	private final Map<Integer, V> values = new HashMap<>();
	
	private final ResourcesHolderAPI resources;
	
	private final Path root;
	
	public Objects(Class<? extends V> clazz, Path file, ResourcesHolderAPI resources) throws JsonParseException, JsonMappingException, IOException{
		this.root = null;
		this.resources = resources;
		load(file, clazz);
	}
	
	public Objects(Path root, Class<? extends V> clazz, ResourcesHolderAPI resources) throws JsonParseException, JsonMappingException, IOException{
		this.root = root;
		this.resources = resources;
		Path file = root.resolve(VMPCSImporter.paths.get(clazz));
		load(file, clazz);
		}
	
	private void load(Path file, Class<? extends V> clazz) throws JsonParseException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		module.addDeserializer(Font.class, new FontDeserializer(resources));
		mapper.registerModule(module);
		
		List<Map<String, Object>> values = mapper.readValue(file.toFile(), new TypeReference<List<Map<String, Object>>>() {
		});
		for (Map<String, Object> o : values){
			int id = (int) o.remove("id");
			Integer fontId = (Integer) o.remove("font");
			V value = mapper.convertValue(o, clazz);
			if (fontId != null)
				addFont(value, id);
			this.values.put(id, value);
		}	
	
	}
	
	private void addFont(V v, int id) throws JsonParseException, JsonMappingException, IOException{
		Objects<Font> fonts = new Objects<>(root, Font.class, resources);
		Class<?> clazz = v.getClass();
		try{
			Method setFont = clazz.getMethod("setFont", Font.class);
			setFont.invoke(v, fonts.getObjectByID(id));
		}
		catch (Exception e){
			//throw new IOException(e);
		}
		
	}
	
	public V getObjectByID(int id){
		V v = values.get(id);
		try {
			Method method = v.getClass().getMethod("clone");
			@SuppressWarnings("unchecked")
			V clone = (V) method.invoke(v);
			return clone;
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			return v;
			
		}
		
	}

}
