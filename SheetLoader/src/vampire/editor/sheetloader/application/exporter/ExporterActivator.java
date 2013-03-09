package vampire.editor.sheetloader.application.exporter;

import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class ExporterActivator implements Activator{

	@Override
	public void setManager(ManagerAPI manager) {
		manager.addExporter(new Exporter(manager.getResourcesHolder()));
	}

}
