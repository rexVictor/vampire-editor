package vampire.editor.copyright.persistency;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.copyright.domain.Project;

public class CopyrightLoader {
	
	public List<Project> loadCopyright(Path path){
		try ( InputStream stream = Files.newInputStream(path)){
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(stream, new TypeReference<List<Project>>() {});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
