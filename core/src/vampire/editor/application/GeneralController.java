package vampire.editor.application;

import vampire.editor.application.sheet.controller.SheetController;
import vampire.editor.plugin.Manager;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;
import vampire.editor.plugin.api.plugin.ControllerUICommunicatorAPI;

public class GeneralController {
	
	private final Manager manager;
	
	private SheetController currentController = null;
	
	
	public GeneralController(Manager manager) {
		super();
		this.manager = manager;
		

	}



	public void open(SheetAPI sheet){
		
	}

}
