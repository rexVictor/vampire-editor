package vampire.editor.sheetloader.application;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;


import vampire.editor.domain.sheet.Sheet;
import vampire.editor.plugin.Manager;


public class SheetParserTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, InstantiationException, IllegalAccessException, IOException {
		Path path = Paths.get("sheetpersistencyprototype");
		SheetParser parser = new SheetParser(path, new Manager());
		Sheet sheet = parser.getSheet();
		System.out.println(sheet.getCategories());
		fail("Not yet implemented");
	}

}
