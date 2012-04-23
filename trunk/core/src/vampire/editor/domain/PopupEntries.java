package vampire.editor.domain;

import java.util.LinkedList;
import java.util.List;

public class PopupEntries {
	
	private final List<PopupEntries> subMenus = new LinkedList<>();
	
	private final List<String> items = new LinkedList<>();
	
	private final PopupEntries parent;
	
	
	
	public PopupEntries(PopupEntries parent) {
		super();
		this.parent = parent;
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

	public List<PopupEntries> getSubMenus() {
		return subMenus;
	}

	public List<String> getItems() {
		return items;
	}
	
	private boolean alreadyInserted(PopupEntries entries){
		if (parent==null) {
			return subMenus.contains(entries);
		}
		else {
			return subMenus.contains(entries)||parent.alreadyInserted(entries);
		}
			
	}
	

	
	
	
	

}
