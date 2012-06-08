package vampire.editor.sheetloader.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.csvreader.CsvReader;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;
import vampire.editor.sheetloader.application.ViewParser;

public class ViewParserTest {

	
	private Path testFolder = Paths.get("testfiles", "valueviews");
	
	private static ViewParser parser;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	@BeforeClass
	public static void prepare() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		parser = new ViewParser();
	}
	
	@Test
	public void testParseValueViewAttributes() throws JsonParseException, JsonMappingException, IOException {
		Path testFile = testFolder.resolve("test1.json");
		CsvReader reader = new CsvReader(testFolder.resolve("test1.csv").toAbsolutePath().toString());
		@SuppressWarnings("unchecked")
		Map<String, List<Object>> map = mapper.readValue(testFile.toFile(), Map.class);
		List<Object> atts = map.get("testCases");
		for (Object o : atts){
			IValueViewAttributes viewAtts = parser.parseValueViewAttributes(o);
			reader.readRecord();
			String[] strings = reader.getValues();
			assertTrue(strings[0].equalsIgnoreCase(((Boolean) viewAtts.isDynamic()).toString()));
			assertTrue(strings[1].equalsIgnoreCase(((Boolean) viewAtts.isShowSpace()).toString()));
			assertEquals(Integer.parseInt(strings[2]), viewAtts.getCircles());			
		}
		
	}

}
