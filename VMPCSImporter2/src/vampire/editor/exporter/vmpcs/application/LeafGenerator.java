package vampire.editor.exporter.vmpcs.application;

import java.awt.Font;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import vampire.editor.fileformat.vmpcs.domain.ClassToFileMapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class LeafGenerator {
	
	private static final TypeReference<Map<String, Object>> TYPE_REFERENCE = new TypeReference<Map<String,Object>>(){};
	
	private final Map<Class<?>, List<Map<String, Object>>> map = new HashMap<>();
	
	private final ObjectMapper converter;
	
	private final ObjectMapper writer;
	
	private final FontIdSerializer serializer;
	
	public LeafGenerator(){
		converter = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		serializer = new FontIdSerializer();
		module.addSerializer(serializer);
		converter.registerModule(module);
		
		writer = new ObjectMapper();
		module = new SimpleModule();
		FontSerializer fontSerializer = new FontSerializer();
		module.addSerializer(fontSerializer);
		writer.registerModule(module);
	}
	
	
	public void generateLeafs(Map<Object, Integer> viewAtts, Path path) throws IOException{
		addToMap(converter, viewAtts);
		Map<Font, Integer> fonts = serializer.getFonts();
		addToMap(writer, fonts);
		serialize(path);
	}
	
	private void addToMap(ObjectMapper mapper, Map<? extends Object, Integer> viewAtts){
		Set<? extends Object> set = viewAtts.keySet();
		for (Object object : set){
			Map<String, Object> map = mapper.convertValue(object, TYPE_REFERENCE);
			map.put("id", viewAtts.get(object));
			List<Map<String, Object>> list = this.map.get(object.getClass());
			if (list == null){
				list = new LinkedList<>();
				this.map.put(object.getClass(), list);
			}
			list.add(map);
		}
	}
	
	private void serialize(Path path) throws IOException{
		Set<Class<?>> classes = map.keySet();
		writer.configure(SerializationFeature.INDENT_OUTPUT, true);
		for (Class<?> clazz : classes){
			String fileName = ClassToFileMapper.paths.get(clazz);
			Path file = path.resolve(fileName);
			OutputStream stream = Files.newOutputStream(file);
			writer.writeValue(stream, map.get(clazz));
		}
	}

}
