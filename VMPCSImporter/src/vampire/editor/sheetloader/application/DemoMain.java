package vampire.editor.sheetloader.application;

import java.nio.file.Path;
import java.nio.file.Paths;

import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.sheetloader.application.importer.VMPCSImportException;
import vampire.editor.sheetloader.application.importer.VMPCSImporter;

public class DemoMain {
	
	public static void main(String[] args) throws VMPCSImportException{
		if (args.length == 0){
			System.out.println("No valid path in arguments");
			return;
		}
		Path path = Paths.get(args[0]);
		VMPCSImporter importer = new VMPCSImporter(new ResourcesHolderEmptyImplementation(), path);
		VampireDocumentAPI documentAPI = importer.load();
		System.out.println(documentAPI);
	}

}
