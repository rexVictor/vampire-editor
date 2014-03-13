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

import java.awt.Container;
import java.awt.Dialog.ModalityType;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import vampire.editor.copyright.domain.Project;
import vampire.editor.copyright.persistency.CopyrightLoader;
import vampire.editor.copyright.view.View;
import vampire.editor.gui.swing.application.SheetViewFactory;
import vampire.editor.gui.swing.mainframe.view.MainFrame;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.plugin.GUIPlugin;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.plugin.Trigger;

public class GuiFacade implements GUIPlugin{
	
//private final ManagerAPI manager;
	
	private final MainFrame mainFrame;
	
	private final MenuBarController menuBarController;
	
	private final SheetViewFactory factory;
	
	private final JFileChooser chooser = new JFileChooser();
	
	private final View copyrightView;
	
	public GuiFacade(ManagerAPI manager){
//		this.manager = manager;
		CopyrightLoader loader = new CopyrightLoader();
		List<Project> projects = loader.loadCopyright(Paths.get("resources", "copyright.json"));
		copyrightView = new View(projects);
		DictionaryAPI dictionary = manager.getResourcesHolder().getDictionary("general");
		menuBarController = new MenuBarController(dictionary);
		mainFrame = new MainFrame(menuBarController.getMenuBar(), manager);
		factory = new SheetViewFactory(manager.getResourcesHolder());
		menuBarController.addMenuItem(new Printer(manager), "file", "print");
		menuBarController.addMenuItem(new Trigger() {
			
			@Override
			public void leftClicked() {
				copyrightView.showDialog();
			}
		}, "help", "copyright");
	}

	@Override
	public void addItemToMenuBar(Trigger trigger, String... menus) {
		menuBarController.addMenuItem(trigger, menus);
		
	}

	@Override
	public String openFileView() {
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.showOpenDialog(null);
		if (chooser.getSelectedFile() == null)
			return null;
		return chooser.getSelectedFile().getAbsolutePath();
		
	}

	public void createErrorMessage(String message) {
		JDialog dialog = new JDialog();
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		JTextArea textArea = new JTextArea(10,50);
		textArea.setText(message);
		textArea.setLineWrap(true);
		dialog.add(textArea);
		dialog.pack();
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		
	}
	
	public void setVisible(){
		mainFrame.setVisible();
	}

	public SheetViewFactory getFactory() {
		return factory;
	}

	public void sheetLoaded(SheetControllerAPI controller) {
		mainFrame.addSheetView(controller);
		
		
	}

	@Override
	public void addImportFileExtension(final String extension) {
		FileFilter filter = new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Vampire Document (*."+extension+")";
			}
			
			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				return f.toString().endsWith(extension);
			}
		};
		chooser.addChoosableFileFilter(filter);
	}

	@Override
	public String saveFileView(final String format) {
		JFileChooser chooser = new JFileChooser();
		FileFilter filter = new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Vampire Document (*."+format+")";
			}
			
			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				return f.toString().endsWith("."+format);
			}
		};
		chooser.setFileFilter(filter);
		chooser.showSaveDialog(null);
		File selected = chooser.getSelectedFile();
		if (selected == null)
			return null;
		return selected.getAbsolutePath();
	}

	@Override
	public boolean demandUserChoise(String message) {
		int option = JOptionPane.showConfirmDialog(null, message, "", JOptionPane.YES_NO_OPTION);
		switch (option) {
		case JOptionPane.YES_OPTION: return true;
		case JOptionPane.NO_OPTION: return false;
		default: return false;
		}
	}

	@Override
	public <V> void addPluginComponent(V component) {
		if (component instanceof Container){
			mainFrame.addPluginComponent((Container) component);
		}
	}
	
	

}
