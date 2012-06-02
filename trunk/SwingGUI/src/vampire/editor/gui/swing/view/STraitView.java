package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.debug.FormDebugPanel;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.view.events.TraitViewListener;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;

public class STraitView implements TraitView, ActionListener{
	
	private final JPanel panel = new FormDebugPanel();
	
	private final JTextField textField = new JTextField();
	
	private final SValueView valueView;
	
	private final DictionaryAPI dictionary;
	
	private final ITraitViewAttributes attributes;
	
	private final FormLayout layout = new FormLayout();
	
	private final List<TraitViewListener> listeners = new LinkedList<>();
	
	public STraitView(SValueView valueView, DictionaryAPI dictionary,
			ITraitViewAttributes attributes) {
		super();
		this.valueView = valueView;
		this.dictionary = dictionary;
		this.attributes = attributes;
		initialize();
	}

	private void initialize() {
		panel.setLayout(layout);
		textField.setEditable(attributes.isEditable());
		textField.setFocusable(attributes.isEditable());
		textField.setBorder(null);
		textField.setBackground(Color.WHITE);
		textField.setFont(attributes.getFont());
		
		panel.setBackground(Color.WHITE);
		textField.addActionListener(this);
		switch (attributes.getOrientation()){
			case HORIZONTAL: {
				layout.appendColumn(ColumnSpec.decode("pref:GROW"));
				layout.appendColumn(ColumnSpec.decode("1px"));
				layout.appendColumn(ColumnSpec.decode("min"));
				layout.appendRow(RowSpec.decode("pref"));
				
				CellConstraints constraints = new CellConstraints();
				constraints.gridHeight 	= 	1;
				constraints.gridWidth	= 	1;
				constraints.gridX		=	1;
				constraints.gridY		=	1;
				constraints.hAlign		=	CellConstraints.FILL;
				
				panel.add(textField, constraints);
				
				constraints.gridX 		= 	3;
				constraints.hAlign		=	CellConstraints.RIGHT;
				
				panel.add(valueView.getView(), constraints);
			} break;
			case VERTICAL: {
				layout.appendRow(RowSpec.decode("pref"));
				layout.appendRow(RowSpec.decode("pref"));
				layout.appendColumn(ColumnSpec.decode("pref:GROW"));
				
				CellConstraints constraints = new CellConstraints();
				constraints.gridHeight	=	1;
				constraints.gridWidth	=	1;
				constraints.gridX		=	1;
				constraints.gridY		=	1;
				constraints.hAlign		=	CellConstraints.CENTER;
				
				panel.add(textField, constraints);
				
				constraints.gridY		=	2;
				constraints.hAlign		=	CellConstraints.FILL;
				
				panel.add(valueView.getView(), constraints);
			} break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String input = textField.getText();
		String translated = dictionary.getKey(input);
		STraitViewEvent event = new STraitViewEvent(translated);
		for (TraitViewListener l : listeners){
			l.traitNameChanged(event);
		}
		
	}

	@Override
	public void setName(String name) {
		String newName = dictionary.getValue(name);
		textField.setText(newName);
	}

	@Override
	public void addListener(TraitViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public ValueView getValueView() {
		return valueView;
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	public JTextField getField(){
		return textField;
	}
	

}
