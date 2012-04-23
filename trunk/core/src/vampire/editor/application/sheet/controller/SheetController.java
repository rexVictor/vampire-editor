package vampire.editor.application.sheet.controller;

import vampire.editor.plugin.api.view.sheet.SheetView;
import vampire.editor.plugin.fullapi.sheet.ISheet;

public class SheetController {
	
	private final ISheet sheet;
	
	private final SheetView view;

	public SheetController(ISheet sheet, SheetView view) {
		super();
		this.sheet = sheet;
		this.view = view;
	}
	
	

}
