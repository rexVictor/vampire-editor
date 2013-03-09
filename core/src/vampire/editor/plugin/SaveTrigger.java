package vampire.editor.plugin;

import java.nio.file.Path;
import java.nio.file.Paths;

import vampire.editor.application.GeneralController;
import vampire.editor.application.sheet.controller.SheetController;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.plugin.DocumentExportException;
import vampire.editor.plugin.api.plugin.GUIPlugin;
import vampire.editor.plugin.api.plugin.SheetExporter;
import vampire.editor.plugin.api.plugin.Trigger;

public class SaveTrigger implements Trigger{
	
	private final GeneralController controller;
	
	private final SheetExporter exporter;
	
	private final GUIPlugin gui;
	
	

	public SaveTrigger(GeneralController controller, SheetExporter exporter,
			GUIPlugin gui) {
		super();
		this.controller = controller;
		this.exporter = exporter;
		this.gui = gui;
	}



	@Override
	public void leftClicked() {
		String pathString = gui.saveFileView(exporter.getFormat());
		if (pathString != null){
			Path path = Paths.get(pathString);
			SheetController controller = this.controller.getCurrentController();
			VampireDocumentAPI document = controller.getDocument();
			try {
				exporter.export(document, path);
			} catch (DocumentExportException e) {
				e.printStackTrace();
			}
		}
	}

}
