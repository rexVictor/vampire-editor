package vampire.editor.sheetloader.application;

import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.GUIPlugin;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class LoaderActivator implements Activator{

	@Override
	public void setManager(ManagerAPI manager) {
		ImportTrigger trigger = new ImportTrigger(manager.getGUI(), manager.getResourcesHolder(), manager.getGeneralController());
		GUIPlugin gui = manager.getGUI();
		gui.addItemToMenuBar(trigger, "file", "open");		
	}

}
