package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;

import vampire.editor.domain.sheet.MModelConstructors;
import vampire.editor.domain.sheet.view.MViewConstructors;
import vampire.editor.fileformat.vmpcs.domain.Constructors;
import vampire.editor.plugin.api.domain.sheet.Value;
import vampire.editor.sheetloader.application.importer.mock.ResourcesHolderMock;

public class ValueImportTest {
	
	private Objects<Value> objects;
	
	
	@Before
	public void setup() throws Throwable{
		Constructors.constructors = new MModelConstructors();
		Constructors.viewAttConstructors = new MViewConstructors();
		Path testPath = Paths.get("testcases","values","values.json");
		objects = new Objects<>(Value.class, testPath, new ResourcesHolderMock(), null);
	}

	@Test
	public void testValueImportTest1() throws Throwable{
		Value expected = Constructors.constructors.createValue(0, 10);
		expected.setValue(1);
		expected.setTempValue(1);
		assertEquals(expected, objects.getObjectByID(0));
	}
	
	@Test
	public void testValueImportTest2() throws Throwable{
		Value expected = Constructors.constructors.createValue(0, 10);
		expected.setValue(0);
		expected.setTempValue(0);
		assertEquals(expected, objects.getObjectByID(1));
	}
	
	@Test
	public void testValueImportTest3() throws Throwable{;
		Value expected = Constructors.constructors.createValue(0, 5);
		expected.setValue(1);
		expected.setTempValue(1);
		assertEquals(expected, objects.getObjectByID(2));
	}
	
	@Test
	public void testValueImportTest4() throws Throwable{
		Value expected = Constructors.constructors.createValue(0, 5);
		expected.setValue(0);
		expected.setTempValue(0);
		assertEquals(expected, objects.getObjectByID(3));
	}

}
