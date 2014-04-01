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

import vampire.editor.fileformat.vmpcs.domain.FileNames;
import vampire.editor.fileformat.vmpcs.domain.StringConstants;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapper;
import vampire.editor.plugin.api.domain.sheet.VampireDocument;

public class VMPCSExporter {
	
	private final SheetExporter exporter = new SheetExporter();
	
	private final IdCalculator idCalculator = new IdCalculator();
	
	private final ModelToViewMapGenerator modelToViewMapGenerator = new ModelToViewMapGenerator();
	
	
	public void export(VampireDocument document, Path path) 
			throws JsonGenerationException, JsonMappingException, IOException, URISyntaxException{
		Map<String, String> env = new HashMap<>();
		env.put("create", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		URI fileUri = path.toUri();
		URI zipUri = new URI("jar:" + fileUri.getScheme(), fileUri.getPath(), null); //$NON-NLS-1$
		try(FileSystem fs = FileSystems.newFileSystem(zipUri, env)){
			Path root = fs.getPath("/"); //$NON-NLS-1$
			ModelToViewModelMapper mapper = document.getModelToViewModelMapper();
			Map<Integer, Object> mapidMap = exporter.exportSheet(document.getSheet(), root);
			Map<Object, Integer> viewAttIdMap = idCalculator.generateViewAttsMap(mapper);
			modelToViewMapGenerator.generateModelToViewMap(mapidMap, viewAttIdMap, mapper, root);
			LeafGenerator generator = new LeafGenerator();
			generator.generateLeafs(viewAttIdMap, root);
			generateVersionFile(root);
		}
		document.setPath(path);
	}
	
	private static void generateVersionFile(Path root) throws IOException{
		Properties properties = new Properties();
		properties.setProperty(StringConstants.VERSION, "1.0.0.0"); //$NON-NLS-1$
		Path version = root.resolve(FileNames.VERSION);
		try(OutputStream stream = Files.newOutputStream(version)){
			properties.store(stream, null);
		}
	}
	
	
	
}
