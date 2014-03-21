package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.awt.Font;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import vampire.editor.sheetloader.application.importer.mock.ResourcesHolderMock;

public class FontImportTest {

	@Test
	public void testFontImportTest1() throws Throwable{
		Path fontPath = Paths.get("testcases","fonts");
		Objects<Font> fonts = new Objects<>(fontPath, Font.class, new ResourcesHolderMock(), null, null);
		Font expected = Font.createFont(0, fontPath.resolve("CAS_ANTN.TTF").toFile()).deriveFont(20f);
		assertEquals(expected, fonts.getObjectByID(0));
	}
	
	@Test
	public void testFontImport2() throws Throwable{
		Path fontPath = Paths.get("testcases","fonts");
		Objects<Font> fonts = new Objects<>(fontPath, Font.class, new ResourcesHolderMock(), null, null);
		Font expected = Font.createFont(0, fontPath.resolve("CAS_ANTN.TTF").toFile()).deriveFont(24f);
		assertEquals(expected, fonts.getObjectByID(1));
	}
	
	@Test
	public void testFontImport3() throws Throwable{
		Path fontPath = Paths.get("testcases","fonts");
		Objects<Font> fonts = new Objects<>(fontPath, Font.class, new ResourcesHolderMock(), null, null);
		Font expected = new Font("Linux Libertine", 0, 12);
		assertEquals(expected, fonts.getObjectByID(2));
	}

}
