/*******************************************************************************
 * The Sheetview of the vampire editor implemented using Swing.
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
package vampire.editor.gui.swing.application;

import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;


import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.PopupEntriesAPI;


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
