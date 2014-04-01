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
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vampire.editor.gui.swing.domain.Images;
import vampire.editor.gui.swing.view.events.SBloodPoolViewEvent;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.BloodPoolViewAttributesAPI;
import vampire.editor.plugin.api.view.events.BloodPoolViewListener;
import vampire.editor.plugin.api.view.sheet.BloodPoolView;

public class SBloodPoolView implements BloodPoolView, MouseListener{
	
	private final JPanel panel = new JPanel();
	
	private final List<BloodPoolViewListener> listeners = new LinkedList<>();
	
	private final List<JLabel> squares = new ArrayList<>();
	
	private int maxValue = 0;
	
	private Icon emptyBox;
	
	private Icon crossedBox;
	
	private final JPanel entries = new JPanel();
	
	public SBloodPoolView(BloodPoolViewAttributesAPI viewAtts, DictionaryAPI dictionary){
		int size = viewAtts.getSize();
		emptyBox = new ImageIcon(Images.getImage("square_empty", size, size)); //$NON-NLS-1$
		crossedBox = new ImageIcon(Images.getImage("square_crossed", size, size)); //$NON-NLS-1$
		panel.setBackground(Color.WHITE);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JTextField textField = Helper.getTitle(dictionary.getValue("bloodpool"), viewAtts.getFont()); //$NON-NLS-1$
		entries.setBackground(Color.WHITE);
		panel.setBackground(Color.WHITE);
		panel.add(textField);
		entries.setLayout(new GridLayout(0,10,0,5));
		panel.add(entries);
		
		setMaxValue(10);
	}
	
	@Override
	public void addListener(BloodPoolViewListener listener) {
		listeners.add(listener);
	}

	@Override
	public void setValue(int value) {
		for (int i = 0; i < value; i++){
			squares.get(i).setIcon(crossedBox);
		}
		for (int i = value; i < maxValue; i++){
			squares.get(i).setIcon(emptyBox);
		}
	}
	
	private void setValue0(int value){
		setValue(value);
		SBloodPoolViewEvent event = new SBloodPoolViewEvent(value, maxValue);
		for (BloodPoolViewListener listener : listeners){
			listener.valueChanged(event);
		}
	}
	
	private void addSquare(){
		JLabel label = new JLabel(emptyBox);
		label.addMouseListener(this);
		squares.add(label);
		entries.add(label);
	} 
	
	private void removeSquare(){
		int size = squares.size();
		JLabel last = squares.remove(size-1);
		panel.remove(last);
	}
	
	@Override
	public void setMaxValue(int maxValue) {
		if (this.maxValue <= maxValue){
			for(int i = this.maxValue; i < maxValue; i++){
				addSquare();
			}
		}
		else{
			for (int i = maxValue; i < this.maxValue; i++){
				removeSquare();
			}
		}
		this.maxValue = maxValue;
		panel.revalidate();
		panel.repaint();
	}
	
	public JPanel getView(){
		return panel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel label = (JLabel) e.getSource();
		int value = 0;
		switch(e.getButton()){
		case MouseEvent.BUTTON1:
			value = squares.indexOf(label) + 1;
			break;
		case MouseEvent.BUTTON3:
			value = squares.indexOf(label);
		default:
			break;
		}
		setValue0(value);
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
