package vampire.editor.plugin.freebiecalculator.persistency;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.plugin.freebiecalculator.domain.FreebieConfig;

public class ConfigsImporter {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private final TypeReference<Map<String, FreebieConfig>> type = new TypeReference<Map<String,FreebieConfig>>(){};
	
	public Map<String, FreebieConfig> loadConfig(Path path) throws IOException{
		InputStream stream = Files.newInputStream(path);
		Map<String, FreebieConfig> configs = mapper.readValue(stream, type);
		return configs;
	}

}
