package vampire.editor.domain.config;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.api.domain.PopupEntriesAPI;

public class PopupEntries implements PopupEntriesAPI {
	
	private final List<PopupEntries> subMenus = new LinkedList<>();
	
	private final List<String> items = new LinkedList<>();
	
	private final PopupEntries parent;
	
	private final String name;
	
	
	
	public PopupEntries(PopupEntries parent, String name) {
		super();
		this.parent = parent;
		this.name = name;
	}

	public void addSubMenu(PopupEntries entries){
		if (alreadyInserted(entries)) {
			throw new AlreadyInsertedException();
		}
		subMenus.add(entries);
	}
	
	public void addItem(String item){
		items.add(item);
	}

	@Override
	public List<PopupEntries> getSubMenus() {
		return subMenus;
	}

	@Override
	public List<String> getItems() {
		return items;
	}
	
	private boolean alreadyInserted(PopupEntriesAPI entries){
		if (parent==null) {
			return subMenus.contains(entries);
		}
		return subMenus.contains(entries)||parent.alreadyInserted(entries);
		
			
	}

	@Override
	public String getName() {
		return name;
	}
	

	
	
	
	

}
