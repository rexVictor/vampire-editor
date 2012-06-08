package vampire.editor.sheetloader.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import vampire.editor.domain.sheet.Data;
import vampire.editor.domain.sheet.MetaEntry;
import vampire.editor.sheetloader.application.MetaParser;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MetaParserTest {
	
	private Path testFolder = Paths.get("testfiles", "metaentries");
	
	private static MetaParser parser;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	@BeforeClass
	public static void prepare() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		parser = new MetaParser();
	}
	
	
	@Test
	public void testGetMetaEntry() {
		fail("Not yet implemented and write good testcases");
	}

	@Test
	public void testGetMeta() throws JsonParseException, JsonMappingException, IOException, InstantiationException, IllegalAccessException {
		Path testFile = testFolder.resolve("test1.json");
		@SuppressWarnings("unchecked")
		Map<String, List<?>> map = mapper.readValue(testFile.toFile(), Map.class);
		Data<MetaEntry> metas = parser.getMeta(map.get("testCases"));
		System.out.println(metas);
	}

}
