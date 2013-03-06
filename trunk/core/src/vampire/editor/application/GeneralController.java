package vampire.editor.application;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import vampire.editor.application.sheet.controller.SheetController;
import vampire.editor.application.sheet.controller.SheetControllerFactory;
import vampire.editor.domain.config.Config;
import vampire.editor.domain.sheet.VampireDocument;
import vampire.editor.plugin.Manager;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;

import vampire.editor.plugin.api.plugin.GeneralControllerAPI;
import vampire.editor.plugin.api.plugin.SheetViewFactory;
import vampire.editor.plugin.api.view.sheet.SheetView;

public class GeneralController implements GeneralControllerAPI{
	
	private SheetController currentController = null;
	
	private final List<SheetController> controllers = new LinkedList<>();
	
	private final Manager manager;
	
	
	public GeneralController(Config config) {
		super();
		this.manager = new Manager(config, this);
	}
	
	public GeneralController(Config config, String[] initial){
		this(config);
		Path path = Paths.get("", initial);
		manager.open(path);
	}



	@Override
	public void open(VampireDocumentAPI document){
		if (document instanceof VampireDocument){
			SheetViewFactory factory = manager.getGUI().getFactory();
			SheetView sheetView = factory.buildSheetView(document);
			SheetControllerFactory controllerFactory = new SheetControllerFactory();
			SheetController controller = controllerFactory.buildSheetController(document, sheetView);
			controllers.add(controller);
			setCurrentController(controller);
			manager.getGUI().sheetLoaded(controller);
			manager.documentOpened(controller);
		}
	}
	
	private void setCurrentController(SheetController controller){
		this.currentController = controller;
	}
	
	public SheetController getCurrentController(){
		return currentController;
	}

}
