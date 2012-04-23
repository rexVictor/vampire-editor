package vampire.editor.application;

import vampire.editor.application.sheet.controller.SheetController;
import vampire.editor.plugin.Manager;
import vampire.editor.plugin.api.view.sheet.SheetView;
import vampire.editor.plugin.fullapi.sheet.ISheet;

public class GeneralController {
	
	private final Manager manager;
	
	public GeneralController(Manager manager) {
		super();
		this.manager = manager;

	}



	public void open(ISheet sheet){
		SheetFactory factory = new SheetFactory(manager);
		SheetController controller = factory.build(sheet, manager);
		SheetView view = factory.getSheetView();
	}

}
