package vampire.editor.gui.swing.mainframe.view;

import java.awt.Dialog.ModalityType;

import javax.swing.JDialog;
import javax.swing.JTextPane;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.plugin.Trigger;

public class AboutView implements Trigger{
	
	private final JDialog dialog = new JDialog();
	
	public AboutView(DictionaryAPI dictionary){
		JTextPane pane = new JTextPane();
		pane.setEditable(false);
		pane.setContentType("text/html"); //$NON-NLS-1$
		pane.setText(dictionary.getValue("aboutcontent")); //$NON-NLS-1$
		dialog.add(pane);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
	}
	
	public void showDialog(){
		dialog.pack();
		dialog.setVisible(true);
	}

	@Override
	public void leftClicked() {
		showDialog();
	}
	
	

}
