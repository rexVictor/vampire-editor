package vampire.editor.exporter.vmpcs.application;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapper;
import vampire.editor.plugin.api.domain.sheet.VampireDocument;

public class VMPCSExporter {
	
	private final SheetExporter exporter = new SheetExporter();
	
	private final IdCalculator idCalculator = new IdCalculator();
	
	private final ModelToViewMapGenerator modelToViewMapGenerator = new ModelToViewMapGenerator();
	
	
	public void export(VampireDocument document, Path path) 
			throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException{
		Map<String, String> env = new HashMap<>();
		env.put("create", "true");
		URI fileUri = path.toUri();
		URI zipUri = new URI("jar:" + fileUri.getScheme(), fileUri.getPath(), null);
		FileSystem fs = FileSystems.newFileSystem(zipUri, env);
		Path root = fs.getPath("/");
		ModelToViewModelMapper mapper = document.getModelToViewModelMapper();
		Map<Integer, Object> mapidMap = exporter.exportSheet(document.getSheet(), root);
		Map<Object, Integer> viewAttIdMap = idCalculator.generateViewAttsMap(mapper);
		modelToViewMapGenerator.generateModelToViewMap(mapidMap, viewAttIdMap, mapper, root);
		LeafGenerator generator = new LeafGenerator();
		generator.generateLeafs(viewAttIdMap, root);
		generateVersionFile(root);
		fs.close();
		document.setPath(path);
	}
	
	private void generateVersionFile(Path root) throws IOException{
		Properties properties = new Properties();
		properties.put("version", "1.0.0.0");
		Path version = root.resolve("version.properties");
		OutputStream stream = Files.newOutputStream(version);
		properties.store(stream, null);
		stream.close();
	}
	
	
	
}
