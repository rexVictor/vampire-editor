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
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.HealthViewAttributesAPI;
import vampire.editor.plugin.api.view.events.HealthViewListener;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;
import vampire.editor.plugin.api.view.sheet.HealthView;

public class HorizontalHealthView implements HealthView{
	
	private final JPanel panel = Helper.createPanel();
	
	private final List<HealthViewListener> listeners = new LinkedList<>();
	
	private final List<HealthEntryView> healthEntryViews = new LinkedList<>();
	
	private final DictionaryAPI dictionary;
	
	private final JPanel healthEntriesPanel = Helper.createPanel();
	
	public HorizontalHealthView(DictionaryAPI dictionaryAPI, HealthViewAttributesAPI viewAtts){
		this.dictionary = dictionaryAPI;
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JTextField title = Helper.getTitle(dictionary.getValue("health"), viewAtts.getFont());
		
		healthEntriesPanel.setLayout(new GridLayout(1, 0));
		healthEntriesPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		panel.add(title);
		panel.add(healthEntriesPanel);
		panel.add(Box.createGlue());
		
	}

	@Override
	public List<HealthEntryView> getEntries() {
		return healthEntryViews;
	}

	@Override
	public void addHealthEntryView(HealthEntryView healthEntryView) {
		healthEntryViews.add(healthEntryView);
		HorizontalHealthEntryView view = (HorizontalHealthEntryView) healthEntryView;
		healthEntriesPanel.add(view.getPanel());
		
	}

	@Override
	public void removeHealthEntryView(HealthEntryView healthEntryView) {
		healthEntryViews.remove(healthEntryView);
	}

	@Override
	public void addListener(HealthViewListener listener) {
		listeners.add(listener);
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
}
