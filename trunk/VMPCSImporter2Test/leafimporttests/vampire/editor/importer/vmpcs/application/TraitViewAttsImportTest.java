package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;
import static vampire.editor.plugin.api.domain.sheet.view.Orientation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vampire.editor.domain.sheet.view.MViewConstructors;
import vampire.editor.fileformat.vmpcs.domain.Constructors;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.ViewAttConstructors;
import vampire.editor.sheetloader.application.importer.mock.FontObjectsMock;
import vampire.editor.sheetloader.application.importer.mock.ResourcesHolderMock;

@SuppressWarnings({"nls"})
public class TraitViewAttsImportTest {
	
	private Objects<TraitViewAttributes> objects;
	
	private static ViewAttConstructors constructors;
	
	@BeforeClass
	public static void beforeClass(){
		constructors = new MViewConstructors();
		Constructors.viewAttConstructors = constructors;
	}
	
	@Before
	public void initialize() throws Throwable{
		Path path = Paths.get("testcases","traitviewatts");
		objects = new Objects<>(path, TraitViewAttributes.class,
						new ResourcesHolderMock(), FontObjectsMock.getInstance(), null);
	}

	@Test
	public void testTraitViewImport0() throws Throwable{
		TraitViewAttributes expected = constructors.createTraitViewAttributes();
		expected.setEditable(false);
		expected.setOrientation(HORIZONTAL);
		expected.setFont(FontObjectsMock.CASANT$0$20);
		TraitViewAttributes actual = objects.getObjectByID(0);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTraitViewImport1() throws Throwable{
		TraitViewAttributes expected = constructors.createTraitViewAttributes();
		expected.setEditable(true);
		expected.setOrientation(VERTICAL);
		expected.setFont(FontObjectsMock.CASANT$0$20);
		TraitViewAttributes actual = objects.getObjectByID(1);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTraitViewImport2() throws Throwable{
		TraitViewAttributes expected = constructors.createTraitViewAttributes();
		expected.setEditable(false);
		expected.setOrientation(VERTICAL);
		expected.setFont(FontObjectsMock.CASANT$0$20);
		TraitViewAttributes actual = objects.getObjectByID(2);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testTraitViewImport3() throws Throwable{
		TraitViewAttributes expected = constructors.createTraitViewAttributes();
		expected.setEditable(true);
		expected.setOrientation(HORIZONTAL);
		expected.setFont(FontObjectsMock.CASANT$0$20);
		TraitViewAttributes actual = objects.getObjectByID(3);
		assertEquals(expected, actual);
	}
	

}
