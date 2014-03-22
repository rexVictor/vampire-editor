package vampire.editor.plugin.metapopupentryadder;

import java.util.Map;

import javax.swing.JPopupMenu;

import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class Constructor implements Activator{
	
	@Override
	public void setManager(ManagerAPI manager) {
		Loader loader = new Loader(manager.getResourcesHolder().getDictionary("sheet"));
		Map<String, JPopupMenu> map = loader.loadFiles();
		Module module = new Module(map);
		manager.getGUI().getFactory().getMetaEntryViewFactory().add(module);
	}

}
