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

import java.awt.Container;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class MainFrame {
	
//	private final JMenuBar menuBar;
	
	private final JPanel contentPane = new JPanel();
	
	private final JDesktopPane pluginPanel = new JDesktopPane();
	
//	private final JTabbedPane tabs = new JTabbedPane();
	
	private final JFrame frame = new JFrame();
	
	private final SheetViewTabPanel tabPanel;
	
	private final FormLayout layout = new FormLayout("10cm, pref, 10cm", "20cm");
	
	
	public MainFrame(JMenuBar menuBar, ManagerAPI manager) {
		super();
//		this.menuBar = menuBar;
		
		contentPane.setLayout(layout);
		frame.setContentPane(contentPane);
		frame.setSize(1000, 700);
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabPanel = new SheetViewTabPanel(manager);
		CellConstraints constraints = new CellConstraints(2,1,1,1);
		contentPane.add(tabPanel.getTabbedPane(), constraints);
		constraints.gridX = 1;
		constraints.hAlign = CellConstraints.FILL;
		constraints.vAlign = CellConstraints.FILL;
		contentPane.add(pluginPanel, constraints);
	}
	
	public void setVisible(){
		frame.setVisible(true);
	}
	
	public void addSheetView(SheetControllerAPI controllerAPI){
		tabPanel.add(controllerAPI);
	}
	
	public JFrame getFrame(){
		return frame;
	}
	
	public void addPluginComponent(Container container){
		JInternalFrame internalFrame = new JInternalFrame();
		internalFrame.setContentPane(container);
		internalFrame.pack();
		pluginPanel.add(internalFrame);
		internalFrame.setVisible(true);
	}

}
