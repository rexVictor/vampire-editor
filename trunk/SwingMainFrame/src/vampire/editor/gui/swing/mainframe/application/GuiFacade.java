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

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;

import vampire.editor.copyright.application.CopyrightViewFactory;
import vampire.editor.copyright.view.CopyrightView;
import vampire.editor.gui.swing.application.factories.SSheetViewFactory;
import vampire.editor.gui.swing.mainframe.view.AboutView;
import vampire.editor.gui.swing.mainframe.view.MainFrame;
import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.plugin.GUIPlugin;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.plugin.Trigger;

@SuppressWarnings("synthetic-access")
public class GuiFacade implements GUIPlugin{
	
	private final MainFrame mainFrame;
	
	private final MenuBarController menuBarController;
	
	private final SSheetViewFactory factory;
	
	private final JFileChooser chooser = new JFileChooser();
	
	private final CopyrightView copyrightView = CopyrightViewFactory.buildView(Paths.get("resources", "copyright.json")); //$NON-NLS-1$ //$NON-NLS-2$
	
	public GuiFacade(ManagerAPI manager){
		DictionaryAPI dictionary = manager.getResourcesHolder().getDictionary(DictionaryAPI.GENERAL_DICTIONARY);
		menuBarController = new MenuBarController(dictionary);
		mainFrame = new MainFrame(menuBarController.getMenuBar(), manager);
		factory = new SSheetViewFactory(manager.getResourcesHolder());
		menuBarController.addMenuItem(new Printer(manager), "file", "print"); //$NON-NLS-1$ //$NON-NLS-2$
		menuBarController.addMenuItem(new Trigger() {
			
			@Override
			public void leftClicked() {
				copyrightView.showDialog();
			}
		}, "help", "copyright"); //$NON-NLS-1$ //$NON-NLS-2$
		
		menuBarController.addMenuItem(new AboutView(dictionary), "help", "about"); //$NON-NLS-1$ //$NON-NLS-2$
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

	@Override
	public void createErrorMessage(String message) {
		JDialog dialog = new JDialog();
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		JTextArea textArea = new JTextArea(10,50);
		textArea.setText(message);
		textArea.setLineWrap(true);
		dialog.add(textArea);
		dialog.pack();
		dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
	}
	
	@Override
	public void createErrorMessage(Throwable e){
		createErrorMessage(e.toString());
	}
	
	@Override
	public void setVisible(){
		mainFrame.setVisible();
	}

	@Override
	public SSheetViewFactory getFactory() {
		return factory;
	}

	@Override
	public void sheetLoaded(SheetControllerAPI controller) {
		mainFrame.addSheetView(controller);
		
		
	}

	@Override
	public void addImportFileExtension(final String extension) {
		FileFilter filter = new FileFilter() {
			
			@Override
			public String getDescription() {
				return "Vampire Document (*."+extension+")"; //$NON-NLS-1$ //$NON-NLS-2$
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
				return "Vampire Document (*."+format+")"; //$NON-NLS-1$ //$NON-NLS-2$
			}
			
			@Override
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				return f.toString().endsWith("."+format); //$NON-NLS-1$
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
		int option = JOptionPane.showConfirmDialog(null, message, "", JOptionPane.YES_NO_OPTION); //$NON-NLS-1$
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
