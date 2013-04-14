package vampire.editor.importer.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import vampire.editor.sheetloader.application.importer.mock.ResourcesHolderMock;

public class CanHandleTest {
	
	VMPCSImporter importer = new VMPCSImporter(new ResourcesHolderMock());

	@Test
	public void testCanHandleExpectTrue() {
		Path path = Paths.get("testcases","canhandle","testtrue.vmpcs");
		assertTrue(importer.canHandle(path));
	}
	
	@Test
	public void testCanHandleExpectFalse1() {
		Path path = Paths.get("testcases","canhandle","testfalse1.vmpcs");
		assertFalse(importer.canHandle(path));
	}
	
	@Test
	public void testCanHandleExpectTrue2() {
		Path path = Paths.get("testcases","canhandle","testfalse2.vmpcs");
		assertFalse(importer.canHandle(path));
	}

}
