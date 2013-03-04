package vampire.editor.gui.swing.mainframe.application;

import vampire.editor.gui.swing.domain.Images;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class GuiActivator implements Activator{

	@Override
	public void setManager(ManagerAPI manager) {
		try {
			Class.forName(Images.class.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GuiFacade facade = new GuiFacade(manager);
		manager.setGUIPlugin(facade);
	}

}
