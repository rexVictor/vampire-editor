package vampire.editor.sheetloader.application;

import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class LoaderActivator implements Activator{

	@Override
	public void setManager(ManagerAPI manager) {
		VMPCSLoader importer = new VMPCSLoader(manager.getResourcesHolder());
		manager.addImporter(importer);
	}

}
