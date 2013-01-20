package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.ResourcesHolderAPI;

public interface ManagerAPI {
	
	public GUIPlugin getGUI();
	
	public ResourcesHolderAPI getResourcesHolder();
	
	public void setGUIPlugin(GUIPlugin gui);
	
	public GeneralControllerAPI getGeneralController();
	

}
