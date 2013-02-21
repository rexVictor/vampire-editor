package vampire.editor.sheetloader.application.exporter;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import vampire.editor.domain.sheet.VampireDocument;
import vampire.editor.sheetloader.application.importer.ResourcesHolderTestImplementation;
import vampire.editor.sheetloader.application.importer.VMPCSImporter;

public class SheetExporterTest {

	@Test
	public void test() throws JsonGenerationException, JsonMappingException, IOException {
		Path path = Paths.get("sheetpersistencyprototype");
		VMPCSImporter importer = new VMPCSImporter(new ResourcesHolderTestImplementation(), path);
		VampireDocument document = importer.load();
		SheetExporter exporter = new SheetExporter(document, new ResourcesHolderTestImplementation());
		Path path2 = Paths.get("export1");
		System.out.print(exporter.export(path2));
		fail("Not yet implemented");
	}

}
