package vampire.editor.gui.swing.application;

import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


import vampire.editor.plugin.api.domain.PopupEntriesAPI;
import vampire.editor.plugin.api.plugin.DictionaryAPI;


public class PopupMenuBuilder {
	
	private final DictionaryAPI dictionary;
	
	
	
	public PopupMenuBuilder(DictionaryAPI dictionary) {
		super();
		this.dictionary = dictionary;
	}

	public JPopupMenu createJPopupMenu(PopupEntriesAPI entries){
		JPopupMenu popupMenu = new JPopupMenu();
		List<? extends PopupEntriesAPI> subMenus = entries.getSubMenus();
		for (PopupEntriesAPI subMenu : subMenus){
			JMenu jMenu = new JMenu();
			jMenu.setText(dictionary.getValue(subMenu.getName()));
			parse(subMenu,jMenu);
			popupMenu.add(jMenu);
		}
		List<String> items = entries.getItems();
		for (String item : items){
			JMenuItem jItem = new JMenuItem();
			jItem.setText(dictionary.getValue(item));
			popupMenu.add(jItem);
		}
		
		return popupMenu;
	}
	
	private void parse(PopupEntriesAPI entries, JMenu menu){
		List<? extends PopupEntriesAPI> subMenus = entries.getSubMenus();
		for (PopupEntriesAPI subMenu : subMenus){
			JMenu jMenu = new JMenu();
			jMenu.setText(dictionary.getValue(subMenu.getName()));
			parse(subMenu, jMenu);
			menu.add(jMenu);
			
		}
		List<String> items = entries.getItems();
		for (String item : items){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(dictionary.getValue(item));
			menu.add(menuItem);
		}
	}

}
