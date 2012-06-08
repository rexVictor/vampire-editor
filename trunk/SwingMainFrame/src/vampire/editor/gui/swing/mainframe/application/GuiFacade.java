package vampire.editor.gui.swing.mainframe.application;

import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;

import vampire.editor.plugin.api.view.GUIPlugin;
import vampire.editor.plugin.api.view.Trigger;

public class GuiFacade implements GUIPlugin{

	@Override
	public void addItemToMenuBar(Trigger trigger, String... menus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String openFileView() {
		JFileChooser chooser = new JFileChooser();
		chooser.showOpenDialog(null);
		
		return null;
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
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		dialog.setVisible(true);
		
	}

}
