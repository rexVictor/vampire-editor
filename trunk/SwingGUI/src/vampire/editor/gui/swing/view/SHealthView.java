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
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.HealthViewAttributesAPI;
import vampire.editor.plugin.api.view.events.HealthViewListener;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;
import vampire.editor.plugin.api.view.sheet.HealthView;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class SHealthView implements HealthView{
	
	private final JPanel panel = new JPanel();
	
	private final FormLayout layout = new FormLayout("5px, pref:GROW, 5px, pref:GROW, 5px, pref:GROW, 5px", "pref");
	
	private final List<HealthViewListener> listeners = new LinkedList<>();
	
	private final List<HealthEntryView> healthEntryViews = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock();
	
	private final DictionaryAPI dictionary;
	
	public SHealthView(DictionaryAPI dictionaryAPI, HealthViewAttributesAPI viewAtts){
		this.dictionary = dictionaryAPI;
		panel.setLayout(layout);
		panel.setBackground(Color.WHITE);
		JLabel label = new JLabel();
		label.setFont(viewAtts.getFont());
		label.setText(dictionary.getValue("health"));
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight = 1;
		constraints.gridWidth = 5;
		constraints.gridX = 2;
		constraints.gridY = 1;
		
		panel.add(label, constraints);
	}

	@Override
	public List<HealthEntryView> getEntries() {
		return healthEntryViews;
	}

	@Override
	public void addHealthEntryView(HealthEntryView healthEntryView) {
		healthEntryViews.add(healthEntryView);
		SHealthEntryView view = (SHealthEntryView) healthEntryView;
		layout.appendRow(RowSpec.decode("pref"));
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight = 1;
		constraints.gridWidth = 1;
		constraints.gridX = 2;
		constraints.gridY = layout.getRowCount();
		constraints.hAlign = CellConstraints.LEFT;
		
		panel.add(view.getDescription(), constraints);
		
		constraints.gridX = 4;
		constraints.hAlign = CellConstraints.RIGHT;
		
		panel.add(view.getPenalty(), constraints);
		
		constraints.gridX = 6;
		constraints.hAlign = CellConstraints.CENTER;
		
		panel.add(view.getBox(), constraints);
	}

	@Override
	public void removeHealthEntryView(HealthEntryView healthEntryView) {
		healthEntryViews.remove(healthEntryView);
		throw new UnsupportedOperationException();
	}

	@Override
	public void addListener(HealthViewListener listener) {
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	public FormLayout getLayout(){
		return layout;
	}

}
