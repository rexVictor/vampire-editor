package vampire.editor.sheetloader.application.exporter;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import vampire.editor.domain.sheet.VampireDocument;
import vampire.editor.sheetloader.application.importer.ResourcesHolderTestImplementation;
import vampire.editor.sheetloader.application.importer.VMPCSImporter;

public class SheetExporterTest {

	@Test
	public void test() throws Throwable {
		Path path = Paths.get("defaultsheets","vtmdefault.vmpcs");
		VMPCSImporter importer = new VMPCSImporter(new ResourcesHolderTestImplementation(), path);
		VampireDocument document = importer.load();
		SheetExporter exporter = new SheetExporter(document, new ResourcesHolderTestImplementation());
		Path path2 = Paths.get("export1", "export1.vmpcs");
		exporter.export(path2);
		fail("Not yet implemented");
	}

}
