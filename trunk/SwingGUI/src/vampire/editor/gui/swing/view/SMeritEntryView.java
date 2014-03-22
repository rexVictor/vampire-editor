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

import java.awt.BorderLayout;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttibutesAPI;
import vampire.editor.plugin.api.view.events.MeritEntryViewListener;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;

public class SMeritEntryView implements MeritEntryView, DocumentListener{
	
	private final JTextField textField = Helper.createLimitedTextField(25);
	
	private final JTextField costField = Helper.createNumberOnlyTextField(1);
	
	private final DictionaryAPI dictionary;
	
	private final List<MeritEntryViewListener> listeners = new LinkedList<>();
	
	private final MeritEntryViewAttibutesAPI viewAtts;
	
	private final JPanel panel = Helper.createPanel();
	
	public SMeritEntryView(DictionaryAPI dictionary, MeritEntryViewAttibutesAPI viewAtts) {
		super();
		this.dictionary = dictionary;
		this.viewAtts = viewAtts;
		textField.setFont(viewAtts.getFont());
		costField.setFont(viewAtts.getFont());
		textField.setDocument(new MeritEntryDocument(30, costField));
		textField.getDocument().addDocumentListener(this);
		costField.getDocument().addDocumentListener(this);
		
		costField.setColumns(1);
		
		panel.setLayout(new BorderLayout(10,0));
		panel.add(costField, BorderLayout.WEST);
		panel.add(textField, BorderLayout.CENTER);
		
	}

	@Override
	public void setCost(int cost) {
		if (cost == 0){
			costField.setText("");
		}
		else{
			costField.setText(cost+"");
		}
	}

	@Override
	public void setText(String text) {
		textField.setText(dictionary.getValue(text));
	}
	
	@Override
	public void addListener(MeritEntryViewListener listener) {
		listeners.add(listener);
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getCostField() {
		return costField;
	}
	
	private void changeEvent(Object source){
		String name = dictionary.getKey(textField.getText());
		String costString = costField.getText();
		int cost = 0;
		if (!costString.isEmpty()){
			cost = Integer.parseInt(costString);
		}
		SMeritEntryViewEvent event = new SMeritEntryViewEvent(cost, name);
		if (source  == costField){
			for (MeritEntryViewListener l : listeners){
				l.costChanged(event);
			}
		}
		else if (source == textField){
			for (MeritEntryViewListener l : listeners){
				l.nameChanged(event);
			}
		}
	}

	@Override
	public SMeritEntryView clone(){
		SMeritEntryView clone = new SMeritEntryView(dictionary, viewAtts.clone());
		clone.setPopupMenu(textField.getComponentPopupMenu());
		return clone;
	}

	@Override
	public MeritEntryViewAttibutesAPI getViewAttributes() {
		return viewAtts;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		if (textField.getDocument() == e.getDocument()){
			changeEvent(textField);
		}
		else if (costField.getDocument() == e.getDocument()){
			changeEvent(costField);
		}
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		changedUpdate(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		changedUpdate(e);
	}
	
	public JPanel getPanel(){
		return panel;
	}

	@Override
	public void setPopupMenu(Object object) {
		if (object instanceof JPopupMenu){
			textField.setComponentPopupMenu((JPopupMenu) object);
		}
		
	}
	
}
