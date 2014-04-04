package vampire.editor.gui.swing.view.properties;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JPanel;

import vampire.editor.gui.swing.view.subcategoryviews.ISubCategoryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;

public class SubCategoryViewProperties implements ActionListener{
	
	private final JDialog dialog = new JDialog();
	
	private final ISubCategoryView view;
	
//	private final DictionaryAPI dictionary;
	
	private final JPanel panel = new JPanel();
	
	private final JCheckBox showTitle = new JCheckBox("showTitle"); //$NON-NLS-1$
	
	private final JButton button = new JButton("close"); //$NON-NLS-1$
	
	public SubCategoryViewProperties(DictionaryAPI dictionary, ISubCategoryView view){
	//	this.dictionary = dictionary;
		this.view = view;
		dialog.setContentPane(panel);
		dialog.setModalityType(ModalityType.APPLICATION_MODAL);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(showTitle);
		panel.add(button);
	}
	
	public void showDialog(){
	//	this.viewAtts = viewAtts;
		SubCategoryViewAttributes viewAtts = view.getViewAtts();
		showTitle.setEnabled(viewAtts.isShowTitle());
		dialog.pack();
		dialog.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}
	

}
