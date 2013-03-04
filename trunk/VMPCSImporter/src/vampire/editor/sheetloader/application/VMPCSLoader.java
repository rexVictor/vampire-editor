package vampire.editor.sheetloader.application;

import java.nio.file.Path;

import vampire.editor.domain.sheet.VampireDocument;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.plugin.GeneralControllerAPI;
import vampire.editor.plugin.api.plugin.SheetImporter;
import vampire.editor.sheetloader.application.importer.VMPCSImporter;

public class VMPCSLoader implements SheetImporter {
	
	private final ResourcesHolderAPI resources;
	
	private final GeneralControllerAPI controller;
	
	public VMPCSLoader(ResourcesHolderAPI resources,
			GeneralControllerAPI controller) {
		super();
		this.resources = resources;
		this.controller = controller;
	}

	@Override
	public VampireDocument loadDocument(Path path){
		VMPCSImporter importer = new VMPCSImporter(resources, path);
		VampireDocument document = importer.load();
		controller.open(document);
		return null;
	}

}
