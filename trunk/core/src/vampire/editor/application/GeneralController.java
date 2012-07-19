package vampire.editor.application;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.application.sheet.controller.SheetController;
import vampire.editor.domain.Config;
import vampire.editor.domain.sheet.Sheet;
import vampire.editor.plugin.Manager;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;

import vampire.editor.plugin.api.plugin.GeneralControllerAPI;
import vampire.editor.plugin.api.view.application.SheetViewFactory;
import vampire.editor.plugin.api.view.sheet.SheetView;

public class GeneralController implements GeneralControllerAPI{
	
	private SheetController currentController = null;
	
	private final List<SheetController> controllers = new LinkedList<>();
	
	private final Manager manager;
	
	
	public GeneralController(Config config) {
		super();
		this.manager = new Manager(config, this);
		

	}



	@Override
	public void open(SheetAPI sheet){
		if (sheet instanceof Sheet){
			SheetViewFactory factory = manager.getGUI().getFactory();
			SheetView sheetView = factory.buildSheetView(sheet);
			SheetControllerFactory controllerFactory = new SheetControllerFactory();
			SheetController controller = controllerFactory.buildSheetController((Sheet) sheet, sheetView);
			controllers.add(controller);
			setCurrentController(controller);
			manager.getGUI().sheetLoaded(controller);
		}
	}
	
	private void setCurrentController(SheetController controller){
		this.currentController = controller;
	}
	
	public SheetController getCurrentController(){
		return currentController;
	}

}
