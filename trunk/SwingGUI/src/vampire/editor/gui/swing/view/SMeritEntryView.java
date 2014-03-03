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

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JTextField;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttibutesAPI;
import vampire.editor.plugin.api.view.events.MeritEntryViewListener;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;

public class SMeritEntryView implements MeritEntryView, ActionListener{
	
	private final JTextField textField = new JTextField();
	
	private final JTextField costField = new JTextField();
	
	private final DictionaryAPI dictionary;
	
	private final List<MeritEntryViewListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock();
	
	private final MeritEntryViewAttibutesAPI viewAtts;

	public SMeritEntryView(DictionaryAPI dictionary, MeritEntryViewAttibutesAPI viewAtts) {
		super();
		this.dictionary = dictionary;
		this.viewAtts = viewAtts;
		textField.setFont(viewAtts.getFont());
		costField.setFont(viewAtts.getFont());
		textField.setBorder(null);
		costField.setBorder(null);
		textField.addActionListener(this);
		costField.addActionListener(this);
	}

	@Override
	public void setCost(int cost) {
		costField.setText(cost+"");
	}

	@Override
	public void setText(String text) {
		textField.setText(dictionary.getValue(text));
	}

	@Override
	public void addListener(MeritEntryViewListener listener) {
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}

	public JTextField getTextField() {
		return textField;
	}

	public JTextField getCostField() {
		return costField;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String name = dictionary.getKey(textField.getText());
		String costString = costField.getText();
		try{
			int cost = Integer.parseInt(costString);
			SMeritEntryViewEvent event = new SMeritEntryViewEvent(cost, name);
			if (e.getSource() == costField){
				for (MeritEntryViewListener l : listeners){
					l.costChanged(event);
				}
			}
			else if (e.getSource() == textField){
				for (MeritEntryViewListener l : listeners){
					l.nameChanged(event);
				}
			}
		}
		catch(NumberFormatException exception){
			
		}
	}
	
	@Override
	public SMeritEntryView clone(){
		return new SMeritEntryView(dictionary, viewAtts);
	}

	@Override
	public MeritEntryViewAttibutesAPI getViewAttributes() {
		return viewAtts;
	}
	
	

}
