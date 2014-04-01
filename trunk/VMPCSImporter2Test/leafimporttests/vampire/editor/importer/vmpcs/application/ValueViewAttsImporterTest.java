package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vampire.editor.domain.sheet.view.MViewConstructors;
import vampire.editor.fileformat.vmpcs.domain.Constructors;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.ViewAttConstructors;
import vampire.editor.sheetloader.application.importer.mock.ResourcesHolderMock;

@SuppressWarnings({"nls"})
public class ValueViewAttsImporterTest {
	
	private Objects<ValueViewAttributes> objects;
	
	private static ViewAttConstructors constructors;
	
	@BeforeClass
	public static void beforeClass(){
		constructors = new MViewConstructors();
		Constructors.viewAttConstructors = constructors;
	}
	
	@Before
	public void setup() throws Throwable{
		Path testPath = Paths.get("testcases","valueviewatts");
		objects = new Objects<>(testPath, ValueViewAttributes.class,
						new ResourcesHolderMock(), null, null);
	}

	@Test
	public void testValueViewAttsImportTest1() throws Throwable{
		ValueViewAttributes expected = constructors.createValueViewAttributes();
		expected.setCircles(5);
		expected.setDynamic(true);
		expected.setShowSpace(true);
		expected.setSize(14);
		expected.setTempSquared(false);
		assertEquals(expected, objects.getObjectByID(0));
	}
	
	@Test
	public void testValueViewAttsImportTest2() throws Throwable{
		ValueViewAttributes expected = constructors.createValueViewAttributes();
		expected.setCircles(5);
		expected.setDynamic(false);
		expected.setShowSpace(false);
		expected.setSize(14);
		expected.setTempSquared(false);
		assertEquals(expected, objects.getObjectByID(1));
	}
	
	@Test
	public void testValueViewAttsImportTest3() throws Throwable{
		ValueViewAttributes expected = constructors.createValueViewAttributes();
		expected.setCircles(10);
		expected.setDynamic(false);
		expected.setShowSpace(false);
		expected.setSize(14);
		expected.setTempSquared(false);
		assertEquals(expected, objects.getObjectByID(2));
	}
	
	@Test
	public void testValueViewAttsImportTest4() throws Throwable{
		ValueViewAttributes expected = constructors.createValueViewAttributes();
		expected.setCircles(8);
		expected.setDynamic(false);
		expected.setShowSpace(true);
		expected.setSize(20);
		expected.setTempSquared(true);
		assertEquals(expected, objects.getObjectByID(3));
	}

}
