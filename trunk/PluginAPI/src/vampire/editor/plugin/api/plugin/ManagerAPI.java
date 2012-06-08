package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.SheetAPI;
import vampire.editor.plugin.api.view.GUIPlugin;

public interface ManagerAPI {
	
	public GUIPlugin getGUI();
	
	public SheetAPI getDefaultSheet();
	
	public ResourcesHolderAPI getResourcesHolder();
	

}
