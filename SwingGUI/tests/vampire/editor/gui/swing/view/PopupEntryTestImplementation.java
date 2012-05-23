package vampire.editor.gui.swing.view;

import java.util.ArrayList;
import java.util.List;

import vampire.editor.plugin.api.domain.PopupEntriesAPI;

public class PopupEntryTestImplementation implements PopupEntriesAPI{
	
	private final List<String> items = new ArrayList<>();
	
	{
		items.add("test1");
		items.add("test2");
		items.add("test3");
	}

	@Override
	public List<? extends PopupEntriesAPI> getSubMenus() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public List<String> getItems() {
		// TODO Auto-generated method stub
		return items;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
