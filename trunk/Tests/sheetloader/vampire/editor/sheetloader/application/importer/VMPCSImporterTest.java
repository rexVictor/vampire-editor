package vampire.editor.sheetloader.application.importer;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import vampire.editor.plugin.Manager;

public class VMPCSImporterTest {

	@Test
	public void test() throws VMPCSImportException {
		Path path = Paths.get("sheetpersistencyprototype");
		VMPCSImporter importer = new VMPCSImporter(new Manager().getResourcesHolder());
		importer.load(path);
		
	}

}
