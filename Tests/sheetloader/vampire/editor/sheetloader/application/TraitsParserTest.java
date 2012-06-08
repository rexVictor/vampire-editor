package vampire.editor.sheetloader.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.domain.sheet.Category;
import vampire.editor.domain.sheet.Data;
import vampire.editor.domain.sheet.Trait;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;
import vampire.editor.sheetloader.application.TraitsParser;

public class TraitsParserTest {
	
	private static TraitsParser parser;
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	@BeforeClass
	public static void prepare() throws InstantiationException, IllegalAccessException, ClassNotFoundException{
		parser = new TraitsParser();
	}

	@Test
	public void testGetTrait() throws JsonParseException, JsonMappingException, IOException {
		
		Path path = Paths.get("testfiles", "traits", "test1.json");
		Object object = mapper.readValue(path.toFile(), Object.class);
		System.out.println(object);
		Trait trait = parser.getTrait(object);
		IValueViewAttributes atts = trait.getValue().getViewAtts();
		System.out.println(atts);
		System.out.println(atts.getCircles());
		System.out.println(trait);
		System.out.println(trait.getValue());
		System.out.println(trait.getViewAtts());
		assertTrue(true);
	}
	
	@Test
	public void testGetCats() throws JsonParseException, JsonMappingException, IOException, InstantiationException, IllegalAccessException{
		System.out.println("treNNUNG");
		Path path = Paths.get("SheetPersistencyProtoType", "default.json");
		Map<?, ?> map = mapper.readValue(path.toFile(), Map.class);
		Data<Category> data = parser.getCategories(map.get("traits"));
		System.out.println(data);
		for (Category cat : data){
			System.out.println(cat);
			System.out.println(cat.getViewAtts().isShowLine());
		}
	}

}
