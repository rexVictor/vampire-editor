/*******************************************************************************
 * The GUI of the vampire editor implemented using Swing.
 * Copyright (C) 2013 Matthias Reimchen
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *    
 * For further information see https://code.google.com/p/vampire-editor/ or write a
 * mail to development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.gui.swing.mainframe.application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import vampire.editor.gui.swing.mainframe.domain.ProtoMenuEntry;
import vampire.editor.gui.swing.mainframe.persistence.ProtoMenuEntryLoader;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.plugin.Trigger;

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
		ProtoMenuEntryLoader loader = new ProtoMenuEntryLoader();
		try {
			List<ProtoMenuEntry> entries =  loader.load(Paths.get("resources", "guiconfig", "menubar.json"));
			for (ProtoMenuEntry entry : entries){
				List<List<String>> paths = entry.toStringArray();
				for (List<String> s : paths){
					addMenuItem(null, s.toArray(new String[]{}));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		JMenuItem item = lowMenus.get(toAdd).get(itemName);
		if (item == null){
			item = new JMenuItem(dictionary.getValue(itemName));
			toAdd.add(item);
			lowMenus.get(toAdd).put(itemName, item);
		}
		if (trigger != null){
			item.addActionListener(triggerToActionListener(trigger));
		}
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
			topMenu.add(item);
			lowMenus.put((JMenu)item, new HashMap<String, JMenuItem>());
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
