package vampire.editor.gui.swing.mainframe.application;

import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

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
	
	public GuiFacade(ManagerAPI manager){
//		this.manager = manager;
		DictionaryAPI dictionary = manager.getResourcesHolder().getDictionary("general");
		menuBarController = new MenuBarController(dictionary);
		mainFrame = new MainFrame(menuBarController.getMenuBar());
		factory = new SheetViewFactory(manager.getResourcesHolder());
	}

	@Override
	public void addItemToMenuBar(Trigger trigger, String... menus) {
		menuBarController.addMenuItem(trigger, menus);
		
	}

	@Override
	public String openFileView() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
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
		mainFrame.addSheetView(controller.getView());
		
		
	}
	
	

}
