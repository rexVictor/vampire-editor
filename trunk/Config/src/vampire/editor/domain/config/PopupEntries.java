/*******************************************************************************
 * Vampire Editor Config.
 * Copyright (C) 2013  Matthias Johannes Reimchen
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
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
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
