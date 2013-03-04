package vampire.editor.sheetloader.application;

import java.nio.file.Path;
import java.nio.file.Paths;

import vampire.editor.domain.sheet.VampireDocument;
import vampire.editor.plugin.api.plugin.GUIPlugin;
import vampire.editor.plugin.api.plugin.GeneralControllerAPI;
import vampire.editor.plugin.api.plugin.Trigger;

public class ImportTrigger implements Trigger{
	
	private final GUIPlugin gui;
	
	private final GeneralControllerAPI controller;
	
	private final VMPCSLoader loader;

	public ImportTrigger(GUIPlugin gui, GeneralControllerAPI controller, VMPCSLoader loader) {
		super();
		this.gui = gui;
		this.controller = controller;
		this.loader = loader;
	}

	@Override
	public void leftClicked() {
		String fileAsString = gui.openFileView();
		if (fileAsString != null){
			Path path = Paths.get(fileAsString);
			VampireDocument document = loader.loadDocument(path);
			controller.open(document);
		}
	}

}
