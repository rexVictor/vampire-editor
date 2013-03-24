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
package vampire.editor.gui.swing.mainframe.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import vampire.editor.gui.swing.view.SSheetView;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaEntryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaEntryListener;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.view.sheet.SheetView;

public class SheetViewTabPanel implements ChangeListener{
	
	private class CloseListener implements ActionListener{
		
		private final ManagerAPI manager;
		
		private final SheetControllerAPI controller;
		
		public CloseListener(ManagerAPI manager, SheetControllerAPI controller) {
			super();
			this.manager = manager;
			this.controller = controller;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int position = controllers.indexOf(controller);
			controllers.remove(position);
			tabPane.remove(position);
			manager.closed(controller);
		}
	}
	
	private class TitleRefresher implements MetaEntryListener{
		
		private final TabComponent component;
		
		

		public TitleRefresher(TabComponent component) {
			super();
			this.component = component;
		}

		@Override
		public void valueChanged(MetaEntryEventAPI event) {
			component.setTitle(event.getNewValue());
			
		}

		@Override
		public void keyChanged(MetaEntryEventAPI event) {
			
		}
		
	}
	private final List<SheetControllerAPI> controllers = new ArrayList<>();
	
	private final ManagerAPI manager;
	
	private final JTabbedPane tabPane = new JTabbedPane();
	
	public SheetViewTabPanel(ManagerAPI manager) {
		super();
		this.manager = manager;
		tabPane.addChangeListener(this);
	}

	public void add(SheetControllerAPI sheetController){
		SheetView sheetView = sheetController.getView();
		if (sheetView instanceof SSheetView){
			controllers.add(sheetController);
			tabPane.add(new SheetViewPanel(((SSheetView) sheetView).getBorderView()));
			TabComponent tabComponent = new TabComponent();
			
			MetaEntryControllerAPI nameController = sheetController.getMetaController().getMetaEntryController("name");
			TitleRefresher titleRefresher = new TitleRefresher(tabComponent);
			nameController.addMetaEntryListener(titleRefresher);
			tabComponent.setTitle(nameController.getMetaEntry().getValue());
			int position = tabPane.getTabCount()-1;
			tabComponent.addCloseListener(new CloseListener(manager, sheetController));
			tabPane.setTabComponentAt(position, tabComponent.getComponent());
		}
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		int position = tabPane.getSelectedIndex();
		if (position != -1){
			manager.selectedSheetChanged(controllers.get(position));
		}
		else{
			manager.selectedSheetChanged(null);
		}
		
	}
	
	public JTabbedPane getTabbedPane(){
		return tabPane;
	}
	

}
