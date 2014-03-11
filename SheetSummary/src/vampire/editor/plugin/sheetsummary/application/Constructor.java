package vampire.editor.plugin.sheetsummary.application;

import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.sheetsummary.view.SheetSummaryView;
import vampire.editor.plugin.sheetsummary.view.ViewFactory;

public class Constructor implements Activator{
	
	

	@Override
	public void setManager(ManagerAPI manager) {
		ViewFactory factory = new ViewFactory(manager.getResourcesHolder().getDictionary("sheet"));
		SheetSummaryView view = factory.buildSheetSummaryView();
		PluginController controller = new PluginController(view);
		manager.addDocumentListener(controller);
		manager.getGUI().addPluginComponent(view.getPanel());
	}

}
