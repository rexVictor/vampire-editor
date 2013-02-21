package vampire.editor.sheetloader.application.importer;

import static org.junit.Assert.*;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class FontImportTest {

	@Test
	public void test1() throws JsonParseException, JsonMappingException, IOException, FontFormatException {
		Path fontPath = Paths.get("importtest","fonts");
		Objects<Font> fonts = new Objects<>(fontPath, Font.class, new ResourcesHolderTestImplementation(), null);
		Font expected = Font.createFont(0, fontPath.resolve("CAS_ANTN.TTF").toFile()).deriveFont(20f);
		assertEquals(expected, fonts.getObjectByID(0));
	}
	
	@Test
	public void test2() throws JsonParseException, JsonMappingException, IOException, FontFormatException {
		Path fontPath = Paths.get("importtest","fonts");
		Objects<Font> fonts = new Objects<>(fontPath, Font.class, new ResourcesHolderTestImplementation(), null);
		Font expected = Font.createFont(0, fontPath.resolve("CAS_ANTN.TTF").toFile()).deriveFont(24f);
		assertEquals(expected, fonts.getObjectByID(1));
	}

}
