package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.MeritViewAttributesAPI;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;
import vampire.editor.plugin.api.view.sheet.MeritView;

public class SMeritView implements MeritView{
	
	private final JPanel panel = new JPanel();
	
	private final FormLayout layout = new FormLayout("5px, 30px, 5px, pref:GROW, 5px", "pref");
	
	private final List<MeritEntryView> entries = new LinkedList<>();
	
	public SMeritView(String title, DictionaryAPI dictionary, MeritViewAttributesAPI viewAtts){
		panel.setBackground(Color.WHITE);
		panel.setLayout(layout);
		JTextField textField = new JTextField();
		textField.setBorder(null);
		textField.setEditable(false);
		textField.setText(dictionary.getValue(title));
		textField.setFont(viewAtts.getFont());
		textField.setBackground(Color.WHITE);
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight 	= 	1;
		constraints.gridWidth	=	3;
		constraints.gridX		=	2;
		constraints.gridY		=	1;
		constraints.hAlign		=	CellConstraints.CENTER;
		
		panel.add(textField, constraints);
	}
	

	@Override
	public void addMeritEntryView(MeritEntryView view) {
		if (view instanceof SMeritEntryView){
			entries.add(view);
			SMeritEntryView entryView = (SMeritEntryView) view;
			layout.appendRow(RowSpec.decode("pref"));
			
			CellConstraints constraints = new CellConstraints();
			constraints.gridHeight	=	1;
			constraints.gridWidth	=	1;
			constraints.gridX		=	2;
			constraints.gridY		=	layout.getRowCount();
			constraints.hAlign		=	CellConstraints.LEFT;
			
			panel.add(entryView.getCostField(), constraints);
			
			constraints.gridX	=	4;
			
			constraints.hAlign	=	CellConstraints.FILL;
			
			panel.add(entryView.getTextField(), constraints);
		}
	}


	public JPanel getPanel() {
		return panel;
	}


	@Override
	public List<MeritEntryView> getEntries() {
		return Collections.unmodifiableList(entries);
	}
	
	

}
