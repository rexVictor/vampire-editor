package vampire.editor.plugin.api.domain;

import java.util.List;


public interface PopupEntriesAPI {

	public List<? extends PopupEntriesAPI> getSubMenus();

	public List<String> getItems();
	
	public String getName();

}