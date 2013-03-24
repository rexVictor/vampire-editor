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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import vampire.editor.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.view.events.DataViewListener;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

public class SSubCategoryView implements SubCategoryView{
	
	private final JPanel panel = new JPanel();
	
	private final List<TraitView> traitViews = new ArrayList<>();
	
	private final SubCategoryViewAttributes atts;
	
	private final DictionaryAPI dictionary;
	
	private final FormLayout layout = new FormLayout();
	
	private final List<DataViewListener<TraitView>> listeners = new LinkedList<>();
	
	public SSubCategoryView(SubCategoryViewAttributes atts, DictionaryAPI dictionary, String title) {
		super();
		this.atts = atts;
		this.dictionary = dictionary;
		initialize(title);
	}

	private void initialize(String title){
		panel.setBackground(Color.WHITE);
		panel.setLayout(layout);
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		if (atts.isShowTitle()){
		
			layout.appendRow(RowSpec.decode("pref"));
			JTextField textField = new JTextField();
			textField.setFont(atts.getFont());
			textField.setBackground(Color.WHITE);
			textField.setEditable(false);
			textField.setFocusable(false);
			textField.setBorder(null);
			textField.setText(dictionary.getValue(title));
			
			CellConstraints constraints = new CellConstraints();
			constraints.gridHeight	=	1;
			constraints.gridWidth	=	1;
			constraints.gridX		=	1;
			constraints.gridY		=	1;
			constraints.hAlign		=	CellConstraints.CENTER;
			
			panel.add(textField, constraints);
		}
	}

	@Override
	public List<TraitView> getEntries() {
		return new ArrayList<>(traitViews);
	}

	@Override
	public void add(TraitView entry) {
		STraitView view = (STraitView) entry;
		layout.appendRow(RowSpec.decode("pref"));
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	1;
		constraints.gridY		=	layout.getRowCount();
		constraints.hAlign		=	CellConstraints.FILL;
		
		panel.add(view.getPanel(), constraints);
		traitViews.add(view);
		panel.revalidate();
		panel.repaint();
	}

	@Override
	public void remove(TraitView entry) {
		if (entry instanceof STraitView){
			STraitView view = (STraitView) entry;
			CellConstraints constraints = layout.getConstraints(view.getPanel());
			panel.remove(view.getPanel());
			layout.removeRow(constraints.gridY);
			traitViews.remove(view);
			panel.revalidate();
			panel.repaint();
		}		
	}

	@Override
	public void insert(int pos, TraitView entry) {
		//TODO implement!
		throw new NotImplementedException();
	}

	
	@Override
	public void addListener(DataViewListener<TraitView> listener) {
		this.listeners.add(listener);
		
	}

	@Override
	public void removeListener(DataViewListener<TraitView> listener) {
		listeners.remove(listener);
	}
	
	public JPanel getPanel(){
		return panel;
	}

}
