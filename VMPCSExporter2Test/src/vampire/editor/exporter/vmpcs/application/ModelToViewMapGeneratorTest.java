package vampire.editor.exporter.vmpcs.application;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import vampire.editor.importer.vmpcs.application.VMPCSImporter;
import vampire.editor.plugin.api.domain.sheet.VampireDocument;
import vampire.editor.sheetloader.application.exporter.mock.ResourcesHolderMock;

@SuppressWarnings("nls")
public class ModelToViewMapGeneratorTest {
	
	private VMPCSExporter exporter = new VMPCSExporter();
	
	private VMPCSImporter importer = new VMPCSImporter(new ResourcesHolderMock());
	
	@Test
	public void test() throws Throwable{
		Path vtmdefault = Paths.get("vtmdefault.vmpcs");
		VampireDocument document = importer.loadDocument(vtmdefault);
		Path target = Paths.get("output.vmpcs");
		exporter.export(document, target);
		fail("Not yet implemented");
	}

}
