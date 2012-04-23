package vampire.editor.importer.vcs.plugin;

import vampire.editor.importer.vcs.application.Opener;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.view.GUIPlugin;

public class Activator implements vampire.editor.plugin.api.plugin.Activator{
	
	@Override
	public void setManager(ManagerAPI manager) {
				
		GUIPlugin gui = manager.getGUI();
		
		gui.addItemToMenuBar(new Opener(manager), "file","import","vcsDocument");
		
	}

}
