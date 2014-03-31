package vampire.editor.gui.swing.view.properties;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;

public class SubCategoryViewProperties implements ActionListener{
	
	private final JDialog dialog = new JDialog();
	
//	private final DictionaryAPI dictionary;
	
	private final JPanel panel = new JPanel();
	
	private final JCheckBox showTitle = new JCheckBox("showTitle");
	
	private final JButton button = new JButton("close");
	
	public SubCategoryViewProperties(DictionaryAPI dictionary){
	//	this.dictionary = dictionary;
		dialog.setContentPane(panel);
		panel.add(showTitle);
		panel.add(button);
	}
	
	public void showDialog(SubCategoryViewAttributes viewAtts){
	//	this.viewAtts = viewAtts;
		showTitle.setEnabled(viewAtts.isShowTitle());
		dialog.setVisible(true);
		dialog.pack();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	

}
