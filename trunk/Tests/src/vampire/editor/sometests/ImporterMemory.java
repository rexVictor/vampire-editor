package vampire.editor.sometests;

import static org.junit.Assert.*;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

import vampire.editor.domain.sheet.VampireDocument;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.sheetloader.application.importer.ResourcesHolderTestImplementation;
import vampire.editor.sheetloader.application.importer.VMPCSImportException;
import vampire.editor.sheetloader.application.importer.VMPCSImporter;

@SuppressWarnings("unused")
public class ImporterMemory {

	@Test
	public void test() throws Throwable {
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Start: "+runtime.freeMemory()/1024);
		Path path = Paths.get("defaultsheets", "vtmdefault.vmpcs");
		ResourcesHolderAPI holder = new ResourcesHolderTestImplementation(); 
		VMPCSImporter importer = new VMPCSImporter(holder, path);
		VampireDocument sheet = importer.load();
		
		long beforeGC = runtime.freeMemory();
		System.out.println("Imported: "+beforeGC/1024);
		importer = null;
		runtime.gc();
		long afterGC = runtime.freeMemory();
		System.out.println("After GC: "+afterGC/1024);
		System.out.println((afterGC-beforeGC)/1024);
	}

}
