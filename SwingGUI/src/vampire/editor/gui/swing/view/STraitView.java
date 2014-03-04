/*******************************************************************************
 * The Sheetview of the vampire editor implemented using Swing.
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
package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.gui.swing.view.valueviews.AbstractValueView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributesAPI;
import vampire.editor.plugin.api.view.events.TraitViewListener;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class STraitView implements TraitView, ActionListener, DocumentListener, FocusListener{
	
	private final JPanel panel = new JPanel();
	
	private final JTextField textField = new JTextField();
	
	private final AbstractValueView valueView;
	
	private final DictionaryAPI dictionary;
	
	private final TraitViewAttributes attributes;
	
	private final FormLayout layout = new FormLayout();
	
	private final List<TraitViewListener> listeners = new LinkedList<>();
	
	public STraitView(AbstractValueView valueView, DictionaryAPI dictionary,
			TraitViewAttributes attributes) {
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
		textField.getDocument().addDocumentListener(this);
		textField.addFocusListener(this);
		switch (attributes.getOrientation()){
			case HORIZONTAL: {
				layout.appendColumn(ColumnSpec.decode("pref:GROW(0.9)"));
				layout.appendColumn(ColumnSpec.decode("1px"));
				layout.appendColumn(ColumnSpec.decode("min:GROW(0.1)"));
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
				
				panel.add(valueView.getPanel(), constraints);
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
				
				panel.add(valueView.getPanel(), constraints);
			} break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//textField.invalidate();
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
	
	public TraitView clone(){
		AbstractValueView clonedValueView = valueView.clone();
		TraitViewAttributes clonedTraitViewAttributes = attributes.clone();
		TraitView traitView = new STraitView(clonedValueView, dictionary, clonedTraitViewAttributes);
		return traitView;
	}

	@Override
	public TraitViewAttributesAPI getViewAttributes() {
		return attributes;
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		actionPerformed(null);
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		actionPerformed(null);
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		actionPerformed(null);
	}

	@Override
	public void focusGained(FocusEvent arg0) {}

	@Override
	public void focusLost(FocusEvent arg0) {
		actionPerformed(null);
	}

	@Override
	public void setTooltip(String text) {
		textField.setToolTipText(text);
	}

}
