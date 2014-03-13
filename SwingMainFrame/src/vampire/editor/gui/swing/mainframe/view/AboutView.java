package vampire.editor.gui.swing.mainframe.view;

import javax.swing.JDialog;
import javax.swing.JTextPane;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.plugin.Trigger;

public class AboutView implements Trigger{
	
	private final JDialog dialog = new JDialog();
	
	public AboutView(DictionaryAPI dictionary){
		JTextPane pane = new JTextPane();
		pane.setEditable(false);
		pane.setContentType("text/html");
		pane.setText(dictionary.getValue("aboutcontent"));
		dialog.add(pane);
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
