package vampire.editor.plugin.sheetsummary.application;

import java.util.HashMap;
import java.util.Map;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.plugin.DocumentEventAPI;
import vampire.editor.plugin.api.plugin.DocumentListener;
import vampire.editor.plugin.sheetsummary.view.SheetSummaryView;

public class PluginController implements DocumentListener{
	
	private final ControllerFactory factory;
	
	private final Map<SheetControllerAPI, SheetPointsController> map = new HashMap<>();
	
	

	public PluginController(SheetSummaryView view) {
		super();
		this.factory = new ControllerFactory(view);
	}

	@Override
	public void documentAdded(DocumentEventAPI e) {
		SheetPointsController controller = factory.buildSheetPointsController(e.getSource());
		map.put(e.getSource(), controller);
	}

	@Override
	public void selectedDocumentChanged(DocumentEventAPI e) {
		SheetPointsController controller = map.get(e.getSource());
		controller.update();
	}

	@Override
	public void documentRemoved(DocumentEventAPI e) {
		map.remove(e.getSource());
	}

}
