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
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import vampire.editor.plugin.api.view.events.DataViewListener;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;
import vampire.editor.plugin.api.view.sheet.MetaView;

public class SMetaView implements MetaView{
	
	private final JPanel panel = new JPanel();
	
	private final List<JPanel> coloumns = new LinkedList<>();
	
	private final List<MetaEntryView> metaEntries = new LinkedList<>();
	
	private int added = 0;
	
	public SMetaView(){
		initialize();
	}
	
	private void initialize(){
		panel.setLayout(new GridLayout(1,0,10,0));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		for (int i = 0; i < 3; i++){
			JPanel coloumn = new JPanel();
			coloumn.setLayout(new BoxLayout(coloumn, BoxLayout.Y_AXIS));
			panel.add(coloumn);
			coloumns.add(coloumn);
		}
	}

	@Override
	public List<? extends MetaEntryView> getEntries() {
		return metaEntries;
	}

	@Override
	public void add(MetaEntryView entry) {
		add0(entry);
	}

	@Override
	public void remove(MetaEntryView entry) {
	}

	@Override
	public void insert(int pos, MetaEntryView entry) {
	}

	@Override
	public void addListener(DataViewListener<MetaEntryView> listener) {
	}

	@Override
	public void removeListener(DataViewListener<MetaEntryView> listener) {
	}
	
	public JPanel getPanel(){
		return panel;
	}

	@Override
	public void add0(MetaEntryView entry) {
		SMetaEntryView view = (SMetaEntryView) entry;
		metaEntries.add(entry);
		int lineCount = view.getViewAtts().getLineCount();
		int x = added/4;
		coloumns.get(x).add(view.getPanel());
		added+=lineCount;
	}

}
