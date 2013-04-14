package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vampire.editor.domain.sheet.view.MViewConstructors;
import vampire.editor.fileformat.vmpcs.domain.Constructors;
import vampire.editor.plugin.api.domain.sheet.view.BloodPoolViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.ViewAttConstructors;
import vampire.editor.sheetloader.application.importer.mock.FontObjectsMock;
import vampire.editor.sheetloader.application.importer.mock.ResourcesHolderMock;

public class BloodpoolViewImportTest {

	private Objects<BloodPoolViewAttributes> objects;
	
	private FontObjectsMock fontObjects;
	
	private static ViewAttConstructors constructors ;
	
	@BeforeClass
	public static void beforeClass(){
		constructors = new MViewConstructors();
		Constructors.viewAttConstructors = constructors;
	}
	
	@Before
	public void setup() throws Throwable{
		Path testPath = Paths.get("testcases","healthentryviewatts","healthentryviewatts.json");
		fontObjects = FontObjectsMock.getInstance();
		objects = new Objects<>(BloodPoolViewAttributes.class, testPath,
						new ResourcesHolderMock(), fontObjects);
	}
	
	@Test
	public void testBloodPoolViewAttsImportTest1() throws Throwable{
		BloodPoolViewAttributes expected = constructors.createBloodPoolViewAttributes();
		expected.setFont(fontObjects.getObjectByID(0));
		expected.setSize(14);
		assertEquals(expected, objects.getObjectByID(0));
	}
	
	@Test
	public void testHealthViewAttsImportTest2() throws Throwable{
		BloodPoolViewAttributes expected = constructors.createBloodPoolViewAttributes();
		expected.setFont(fontObjects.getObjectByID(1));
		expected.setSize(20);
		assertEquals(expected, objects.getObjectByID(1));
	}
	
	@Test
	public void testHealthViewAttsImportTest3() throws Throwable{
		BloodPoolViewAttributes expected = constructors.createBloodPoolViewAttributes();
		expected.setFont(fontObjects.getObjectByID(0));
		expected.setSize(24);
		assertEquals(expected, objects.getObjectByID(2));
	}

}
