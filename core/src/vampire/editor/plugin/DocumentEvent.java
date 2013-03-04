package vampire.editor.plugin;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.plugin.DocumentEventAPI;

public class DocumentEvent implements DocumentEventAPI{
	
	private final SheetControllerAPI sheetController;

	public DocumentEvent(SheetControllerAPI sheetController) {
		super();
		this.sheetController = sheetController;
	}

	public SheetControllerAPI getSource() {
		return sheetController;
	}
	
	

}
