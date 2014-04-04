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
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.view.MeritViewAttributesAPI;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;
import vampire.editor.plugin.api.view.sheet.MeritView;

public class SMeritView implements MeritView{
	
	private final JPanel panel = Helper.createPanel();
	
	private final List<MeritEntryView> entries = new LinkedList<>();
	
	private final JPanel entriesPanel = Helper.createPanel();
	
	public SMeritView(String title, DictionaryAPI dictionary, MeritViewAttributesAPI viewAtts){
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JTextField textField = Helper.getTitle(dictionary.getValue(title), viewAtts.getFont());
		panel.add(textField);
		panel.add(entriesPanel);
		entriesPanel.setLayout(new GridLayout(0, 1));
		panel.add(Box.createGlue());
	}
	
	@Override
	public void add(MeritEntryView view){
		add0(view);
	}
	
	@Override
	public void add0(MeritEntryView view) {
		if (view instanceof SMeritEntryView){
			entries.add(view);
			SMeritEntryView entryView = (SMeritEntryView) view;
			entriesPanel.add(entryView.getPanel());
			panel.revalidate();
			panel.repaint();
		}
	}
	
	public JPanel getPanel() {
		return panel;
	}


	@Override
	public List<MeritEntryView> getEntries() {
		return Collections.unmodifiableList(entries);
	}

	@Override
	public void remove(MeritEntryView view) {
		if (view instanceof SMeritEntryView){
			SMeritEntryView v = (SMeritEntryView) view;
			entriesPanel.remove(v.getPanel());
			panel.repaint();
			panel.revalidate();
		}
	}

	@Override
	public void insert(int index, MeritEntryView s) {
		throw new UnsupportedOperationException();
	}
	
	

}
