package vampire.editor.sheetloader.application.importer;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;


public class VMPCSImporterTest {

	@Test
	public void test() throws VMPCSImportException {
		Path path = Paths.get("sheetpersistencyprototype");
		VMPCSImporter importer = new VMPCSImporter(new ResourcesHolderTestImplementation(), path);
		importer.load();
		fail("not yet implemented");
	}

}
