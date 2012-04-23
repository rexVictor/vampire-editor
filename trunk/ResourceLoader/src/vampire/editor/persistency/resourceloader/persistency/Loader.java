package vampire.editor.persistency.resourceloader.persistency;

import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;






public class Loader {
	
	public void test() throws Throwable {
		Path path = Paths.get("resources/traitNames.json");
		ObjectMapper mapper = new ObjectMapper();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = mapper.readValue(path.toFile(), Map.class);
		System.out.println(map.getClass().getName());
		System.out.println(map.get("clans").getClass().getName());
		System.out.println(map.get("merits").getClass().getName());
		System.out.println(map);
		
	}

}
