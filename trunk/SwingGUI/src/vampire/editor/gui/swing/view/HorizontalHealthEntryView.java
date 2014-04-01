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

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vampire.editor.gui.swing.domain.Images;
import vampire.editor.gui.swing.view.events.SHeathEntryViewEvent;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.DamageType;
import vampire.editor.plugin.api.domain.sheet.view.HealthEntryViewAttributesAPI;
import vampire.editor.plugin.api.view.events.HealthEntryViewListener;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;

public class HorizontalHealthEntryView implements HealthEntryView, MouseListener{
	
	private final JLabel box = new JLabel();
	
	private final JTextField penaltyField = Helper.createNumberOnlyTextField(2);
	
	private final List<HealthEntryViewListener> listeners = new LinkedList<>();
	
	private int penalty;
	
	private DamageType damageType;
	
	private JPanel panel = Helper.createPanel();
	
	private Icon emptyBox;
	
	private Icon slashedBox;
	
	private Icon crossedBox;
	
	public HorizontalHealthEntryView(DictionaryAPI dictionary, HealthEntryViewAttributesAPI viewAtts) {
		super();
		int size = viewAtts.getSize();
		emptyBox = new ImageIcon(Images.getImage("square_empty", size, size));
		slashedBox = new ImageIcon(Images.getImage("square_slashed", size, size));
		crossedBox = new ImageIcon(Images.getImage("square_crossed", size, size));
		
		box.addMouseListener(this);
		penaltyField.setFont(viewAtts.getFont());
		
		panel.setLayout(new GridLayout(2, 1));
		panel.add(box);
		panel.add(penaltyField);
	}

	@Override
	public void addListener(HealthEntryViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void setDamageType(DamageType damageType) {
		if (damageType == null){
			box.setIcon(emptyBox);
		}
		else switch(damageType){
		case LETHAL:
		case BASHING: box.setIcon(slashedBox); break;
		case AGGRAVATED: box.setIcon(crossedBox);
		}
		this.damageType = damageType;
	}
	
	private void setDamageType0(DamageType damageType){
		setDamageType(damageType);
		SHeathEntryViewEvent event = makeEvent();
		for (HealthEntryViewListener l : listeners){
			l.damageTypeChanged(event);
		}
	}

	@Override
	public void setPenalty(int penalty) {
		if (penalty == 0){
			this.penaltyField.setText("");
		}
		else{
			this.penaltyField.setText("-"+penalty);
		}
		this.penalty = penalty;
	}
	
	private SHeathEntryViewEvent makeEvent(){
		return new SHeathEntryViewEvent(null, penalty, damageType);
	}

	@Override
	public void setDescription(String description) {}

	
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
	
	public JPanel getPanel(){
		return panel;
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
