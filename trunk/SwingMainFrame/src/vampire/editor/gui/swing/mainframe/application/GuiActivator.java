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

import vampire.editor.gui.swing.domain.Images;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class GuiActivator implements Activator{

	@Override
	public void setManager(ManagerAPI manager) {
		try {
			Class.forName(Images.class.getName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GuiFacade facade = new GuiFacade(manager);
		manager.setGUIPlugin(facade);
	}

}
