package vampire.editor.gui.swing.mainframe.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import vampire.editor.plugin.api.plugin.DictionaryAPI;
import vampire.editor.plugin.api.view.Trigger;

public class MenuBarController {
	
	private class Listener implements ActionListener{
		
		private final Trigger trigger;			

		private Listener(Trigger trigger) {
			super();
			this.trigger = trigger;
		}



		@Override
		public void actionPerformed(ActionEvent e) {
			trigger.leftClicked();
		}
		
	}
	
	private final JMenuBar menubar = new JMenuBar();
	
	private final Map<String, JMenu> topMenus = new HashMap<>();
	
	private final Map<JMenu, Map<String, JMenuItem>> lowMenus= new HashMap<>();
	
	
	
	private final DictionaryAPI dictionary;

	public MenuBarController(DictionaryAPI dictionary) {
		super();
		this.dictionary = dictionary;
	}
	
	public void addMenuItem(Trigger trigger, String... strings){
		List<String> menuNames = new LinkedList<>(Arrays.asList(strings));
		String topMenuName = menuNames.remove(0);
		JMenu topMenu = topMenus.get(topMenuName);
		if (topMenu == null) {
			topMenu = new JMenu(dictionary.getValue(topMenuName));
			menubar.add(topMenu);
			topMenus.put(topMenuName, topMenu);
			lowMenus.put(topMenu, new HashMap<String, JMenuItem>());
		}
		JMenu toAdd = getMenu(topMenu, menuNames);
		String itemName = menuNames.remove(0);
		JMenuItem item = new JMenuItem(dictionary.getValue(itemName));
		toAdd.add(item);
		lowMenus.get(toAdd).put(itemName, item);
		item.addActionListener(triggerToActionListener(trigger));
	}
	
	private JMenu getMenu(JMenuItem topMenu, List<String> strings){
		if (strings.size() == 1) {
			return (JMenu) topMenu;
		}
		Map<String, JMenuItem> items = lowMenus.get(topMenu);
		String next = strings.remove(0);
		JMenuItem item = items.get(next);
		if (item == null){
			item = new JMenu(dictionary.getValue(next));
			items.put(next, item);
		}
		return getMenu(item, strings);		
	}
	
	private ActionListener triggerToActionListener(Trigger trigger){
		return new Listener(trigger);
	}
	
	public JMenuBar getMenuBar(){
		return menubar;
	}
	
	

}
