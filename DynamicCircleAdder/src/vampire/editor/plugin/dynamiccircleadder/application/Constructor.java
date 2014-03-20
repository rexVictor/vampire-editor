package vampire.editor.plugin.dynamiccircleadder.application;

import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.dynamiccircleadder.view.View;

public class Constructor implements Activator{

	@Override
	public void setManager(ManagerAPI manager) {
		View view = new View(manager);
		manager.getGUI().addPluginComponent(view.getPanel());
	}

}
