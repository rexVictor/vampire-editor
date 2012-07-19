package vampire.editor.sheetloader.application;

import java.nio.file.Path;
import java.nio.file.Paths;

import vampire.editor.domain.sheet.Sheet;
import vampire.editor.plugin.api.plugin.GeneralControllerAPI;
import vampire.editor.plugin.api.plugin.ResourcesHolderAPI;
import vampire.editor.plugin.api.view.GUIPlugin;
import vampire.editor.plugin.api.view.Trigger;
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
		Path path = Paths.get(fileAsString);
		VMPCSImporter importer = new VMPCSImporter(resources);
		Sheet sheet = importer.load(path);
		controller.open(sheet);		
	}

}
