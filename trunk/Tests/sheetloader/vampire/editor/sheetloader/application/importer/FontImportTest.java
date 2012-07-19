package vampire.editor.sheetloader.application.importer;

import static org.junit.Assert.*;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class FontImportTest {

	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Path fontPath = Paths.get("sheetpersistencyprototype");
		Objects<Font> fonts = new Objects<>(fontPath, Font.class, new ResourcesHolderTestImplementation());
		int id = 0;
		Font font = null;
		while ((font = fonts.getObjectByID(id)) != null){
			System.out.println(font);
			id++;
		}
		fail("Not yet implemented");
	}

}
