package vampire.editor.persistency.resourceloader.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vampire.editor.domain.PopupEntries;

public class ProtoPopupEntries {
	
	private final Map<String, List<String>> items = new HashMap<>();
	
	private final Map<String, List<String>> subMenus = new HashMap<>();
	
	
	public void addItem(String key, List<String> list){
		items.put(key, list);		
	}
	
	public void addSubMenu(String key, List<String> keys) {
		subMenus.put(key, keys);
	}
	
	
	
	public PopupEntries getPopupEntries(String key){
		PopupEntries entries = new PopupEntries(null);
		if (subMenus.get(key) == null){
			items.get(key);
		}
		return null;
	}
	
	

	

}
