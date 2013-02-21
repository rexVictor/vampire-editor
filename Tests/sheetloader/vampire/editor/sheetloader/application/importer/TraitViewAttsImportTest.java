package vampire.editor.sheetloader.application.importer;

import static org.junit.Assert.*;

import java.awt.Font;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import vampire.editor.domain.sheet.view.TraitViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.Orientation;


public class TraitViewAttsImportTest {

	@Test
	public void testTraitViewImport0() throws Throwable{
		Path path = Paths.get("importtest","traitviewatts");
		Path fontpath = Paths.get("importtest", "traitviewatts", "CAS_ANTN.TTF");
		Font expectedFont = Font.createFont(0, fontpath.toFile()).deriveFont(20f);
		Objects<Font> fontObjects = new Objects<>(path, Font.class, new ResourcesHolderTestImplementation(), null);
		Objects<TraitViewAttributes> objects = 
				new Objects<>(path, TraitViewAttributes.class, new ResourcesHolderTestImplementation(), fontObjects);
		TraitViewAttributes viewAttributes = objects.getObjectByID(0);
		assertEquals(false, viewAttributes.isEditable());
		assertEquals(Orientation.HORIZONTAL, viewAttributes.getOrientation());
		assertEquals(false, viewAttributes.isSquares());
		assertEquals(expectedFont, viewAttributes.getFont());
	}
	
	@Test
	public void testTraitViewImport1() throws Throwable{
		Path path = Paths.get("importtest","traitviewatts");
		Objects<Font> fontObjects = new Objects<>(path, Font.class, new ResourcesHolderTestImplementation(), null);
		Objects<TraitViewAttributes> objects = 
				new Objects<>(path, TraitViewAttributes.class, new ResourcesHolderTestImplementation(), fontObjects);
		TraitViewAttributes viewAttributes = objects.getObjectByID(1);
		assertEquals(true, viewAttributes.isEditable());
		assertEquals(Orientation.VERTICAL, viewAttributes.getOrientation());
		assertEquals(false, viewAttributes.isSquares());
		assertEquals(fontObjects.getObjectByID(0), viewAttributes.getFont());
	}
	
	@Test
	public void testTraitViewImport2() throws Throwable{
		Path path = Paths.get("importtest","traitviewatts");
		Objects<Font> fontObjects = new Objects<>(path, Font.class, new ResourcesHolderTestImplementation(), null);
		Objects<TraitViewAttributes> objects 
			= new Objects<>(path, TraitViewAttributes.class, new ResourcesHolderTestImplementation(), fontObjects);
		
		TraitViewAttributes viewAttributes = objects.getObjectByID(2);
		assertEquals(false, viewAttributes.isEditable());
		assertEquals(Orientation.VERTICAL, viewAttributes.getOrientation());
		assertEquals(false, viewAttributes.isSquares());
		assertEquals(fontObjects.getObjectByID(0), viewAttributes.getFont());
	}
	
	@Test
	public void testTraitViewImport3() throws Throwable{
		Path path = Paths.get("importtest","traitviewatts");
		Objects<Font> fontObjects = new Objects<>(path, Font.class, new ResourcesHolderTestImplementation(), null);
		Objects<TraitViewAttributes> objects = 
				new Objects<>(path, TraitViewAttributes.class, new ResourcesHolderTestImplementation(), fontObjects);
		TraitViewAttributes viewAttributes = objects.getObjectByID(3);
		assertEquals(true, viewAttributes.isEditable());
		assertEquals(Orientation.HORIZONTAL, viewAttributes.getOrientation());
		assertEquals(false, viewAttributes.isSquares());
		assertEquals(fontObjects.getObjectByID(0), viewAttributes.getFont());
	}

}
