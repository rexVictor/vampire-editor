package vampire.editor.application;

import vampire.editor.application.sheet.controller.SheetController;
import vampire.editor.plugin.Manager;
import vampire.editor.plugin.api.view.events.SheetListener;
import vampire.editor.plugin.api.view.sheet.SheetView;
import vampire.editor.plugin.fullapi.sheet.ISheet;

public class SheetFactory {
	
	private final Manager manager;
	
	
	
	public SheetFactory(Manager manager) {
		super();
		this.manager = manager;
	}



	public SheetController build(ISheet sheet, SheetListener listener){
		SheetController controller = new SheetController(sheet, null);
		return null;
	}
	
	public SheetView getSheetView(){
		return null;
	}
	
 

}
