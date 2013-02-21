package vampire.editor.sheetloader.application;

import java.nio.file.Path;
import java.nio.file.Paths;

import vampire.editor.domain.sheet.VampireDocument;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.plugin.GUIPlugin;
import vampire.editor.plugin.api.plugin.GeneralControllerAPI;
import vampire.editor.plugin.api.plugin.Trigger;
import vampire.editor.sheetloader.application.importer.VMPCSImporter;

public class ImportTrigger implements Trigger{
	
	private final GUIPlugin gui;
	
	private final ResourcesHolderAPI resources;
	
	private final GeneralControllerAPI controller;
	
		

	public ImportTrigger(GUIPlugin gui, ResourcesHolderAPI resources, GeneralControllerAPI controller) {
		super();
		this.gui = gui;
		this.resources = resources;
		this.controller = controller;
	}



	@Override
	public void leftClicked() {
		String fileAsString = gui.openFileView();
		if (fileAsString != null){
			Path path = Paths.get(fileAsString);
			VMPCSImporter importer = new VMPCSImporter(resources, path);
			VampireDocument document = importer.load();
			controller.open(document);
		}
	}

}
