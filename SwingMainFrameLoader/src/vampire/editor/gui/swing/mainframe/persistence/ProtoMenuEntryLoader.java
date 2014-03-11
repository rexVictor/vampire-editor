package vampire.editor.gui.swing.mainframe.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.gui.swing.mainframe.domain.ProtoMenuEntry;

public class ProtoMenuEntryLoader {
	
	public List<ProtoMenuEntry> load(Path path) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		try (InputStream stream = Files.newInputStream(path)){
			return mapper.readValue(stream, new TypeReference<List<ProtoMenuEntry>>() {});
		}
	}

}
