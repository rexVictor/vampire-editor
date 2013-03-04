package vampire.editor.sheetloader.application;

import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.GUIPlugin;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class LoaderActivator implements Activator{

	@Override
	public void setManager(ManagerAPI manager) {
		VMPCSLoader importer = new VMPCSLoader(manager.getResourcesHolder(), manager.getGeneralController());
		manager.setDefaultImporter(importer);
		ImportTrigger trigger = new ImportTrigger(manager.getGUI(), manager.getGeneralController(), importer);
		GUIPlugin gui = manager.getGUI();
		gui.addItemToMenuBar(trigger, "file", "open");		
	}

}
