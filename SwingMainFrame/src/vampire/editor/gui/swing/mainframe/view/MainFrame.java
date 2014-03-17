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

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class MainFrame {
	
//	private final JMenuBar menuBar;
	
	private final JPanel contentPane = new JPanel();
	
	private final PluginPanel pluginPanel = new PluginPanel();
	
//	private final JTabbedPane tabs = new JTabbedPane();
	
	private final JFrame frame = new JFrame();
	
	private final SheetViewTabPanel tabPanel;
	
	public MainFrame(JMenuBar menuBar, ManagerAPI manager) {
		super();
		Path iconPath = Paths.get("resources", "guiconfig", "icon.gif");
		Image image = null;
		try ( InputStream stream = Files.newInputStream(iconPath)){
			image = ImageIO.read(stream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		contentPane.setLayout(new BorderLayout());
		frame.setContentPane(contentPane);
		frame.setIconImage(image);
		frame.setTitle("Vampire Editor");
		frame.setSize(1000, 700);
		frame.setJMenuBar(menuBar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tabPanel = new SheetViewTabPanel(manager);
		contentPane.add(tabPanel.getTabbedPane(), BorderLayout.WEST);
		contentPane.add(pluginPanel);
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
		pluginPanel.add((Container) container);
	}

}
