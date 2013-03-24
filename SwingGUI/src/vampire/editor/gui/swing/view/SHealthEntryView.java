/*******************************************************************************
 * The Sheetview of the vampire editor implemended using Swing.
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

import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JLabel;
import javax.swing.JTextField;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.DamageType;
import vampire.editor.plugin.api.domain.sheet.view.HealthEntryViewAttributesAPI;
import vampire.editor.plugin.api.view.events.HealthEntryViewListener;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;

public class SHealthEntryView implements HealthEntryView, MouseListener{
	
	private final JTextField descriptionField = new JTextField();
	
	private final JLabel box = new JLabel();
	
	private final JTextField penaltyField = new JTextField();
	
	private final List<HealthEntryViewListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock();
	
	private int penatly;
	
	private DamageType damageType;
	
	private String descriptionText;
	
	private final DictionaryAPI dictionary;
	
	

	public SHealthEntryView(DictionaryAPI dictionary, HealthEntryViewAttributesAPI viewAtts) {
		super();
		this.dictionary = dictionary;
		descriptionField.setBorder(null);
		penaltyField.setBorder(null);
		box.addMouseListener(this);
		box.setFont(new Font("Sans Serif", 0, viewAtts.getFont().getSize()));
		descriptionField.setFont(viewAtts.getFont());
		penaltyField.setFont(viewAtts.getFont());
	}

	@Override
	public void addListener(HealthEntryViewListener listener) {
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void setDamageType(DamageType damageType) {
		if (damageType == null){
			box.setText("\u2610");
		}
		else switch(damageType){
		case LETHAL:
		case BASHING: box.setText("\u2341"); break;
		case AGGRAVATED: box.setText("\u2612");
		}
		this.damageType = damageType;
	}
	
	private void setDamageType0(DamageType damageType){
		setDamageType(damageType);
		SHeathEntryViewEvent event = makeEvent();
		lock.lock();
		try{
			for (HealthEntryViewListener l : listeners){
				l.damageTypeChanged(event);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void setPenalty(int penalty) {
		this.penaltyField.setText("-"+penalty);
		this.penatly = penalty;
	}
	
	/*private void setPenalty0(int penalty){
		setPenalty(penalty);
		SHeathEntryViewEvent event = makeEvent();
		lock.lock();
		try{
			for (HealthViewEntryListener l : listeners){
				l.penaltyChanged(event);
			}
		}
		finally{
			lock.unlock();
		}
	}*/
	
	private SHeathEntryViewEvent makeEvent(){
		return new SHeathEntryViewEvent(descriptionText, penatly, damageType);
	}

	@Override
	public void setDescription(String description) {
		this.descriptionField.setText(dictionary.getValue(description));
	}

	public JTextField getDescription() {
		return descriptionField;
	}

	public JLabel getBox() {
		return box;
	}

	public JTextField getPenalty() {
		return penaltyField;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (damageType == null){
			setDamageType0(DamageType.LETHAL);
		}
		else switch (damageType) {
		case BASHING:
		case LETHAL: setDamageType0(DamageType.AGGRAVATED); break;
		case AGGRAVATED: setDamageType0(null);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	

}
