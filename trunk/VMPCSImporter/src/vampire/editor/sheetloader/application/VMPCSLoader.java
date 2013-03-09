package vampire.editor.sheetloader.application;

import java.nio.file.Path;

import vampire.editor.domain.sheet.VampireDocument;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.plugin.SheetImporter;
import vampire.editor.sheetloader.application.importer.VMPCSImportException;
import vampire.editor.sheetloader.application.importer.VMPCSImporter;

public class VMPCSLoader implements SheetImporter {
	
	private final ResourcesHolderAPI resources;
	
	public VMPCSLoader(ResourcesHolderAPI resources) {
		super();
		this.resources = resources;
	}

	@Override
	public VampireDocument loadDocument(Path path) throws VMPCSImportException{
		VMPCSImporter importer = new VMPCSImporter(resources, path);
		VampireDocument document = importer.load();
		return document;
	}

	@Override
	public boolean canHandle(Path path) {
		return path.getFileName().toString().endsWith(".vmpcs");
	}

}
