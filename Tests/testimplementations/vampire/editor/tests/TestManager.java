package vampire.editor.tests;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.plugin.GUIPlugin;
import vampire.editor.plugin.api.plugin.GeneralControllerAPI;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class TestManager implements ManagerAPI{

	@Override
	public GUIPlugin getGUI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResourcesHolderAPI getResourcesHolder() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setGUIPlugin(GUIPlugin gui) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GeneralControllerAPI getGeneralController() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void closed(SheetControllerAPI controller) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void selectedSheetChanged(SheetControllerAPI controller) {
		// TODO Auto-generated method stub
		
	}

}
