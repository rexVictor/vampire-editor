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
import java.util.List;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.view.events.DataViewListener;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public class SCategoryView implements CategoryView{
	
	private final JPanel panel = new JPanel();
	
	private final CategoryViewAttributes viewAtts;
	
	private final DictionaryAPI dictionary;
	
	private final FormLayout layout = new FormLayout();
	
	private final List<SSubCategoryView> subCategoryViews = new ArrayList<>();
	
	private final ResourcesHolderAPI resources;
	
	private LineImage title;
	
	

	public SCategoryView(CategoryViewAttributes viewAtts,
			ResourcesHolderAPI resources, String title) {
		super();
		this.viewAtts = viewAtts;
		this.dictionary = resources.getDictionary("sheet");
		this.resources = resources;
		initialize(title);
	}
	
	private void initialize(String titleName){
		panel.setLayout(layout);
		panel.setBackground(Color.WHITE);
		layout.appendColumn(ColumnSpec.decode("10px"));
		if (viewAtts.isShowLine()){
			title = new LineImage(resources.getLine(viewAtts.getImage()));
			title.setTitle(dictionary.getValue(titleName));
			title.setTitleFont(viewAtts.getFont());
			layout.appendRow(RowSpec.decode("pref"));
			
			
			CellConstraints constraints = new CellConstraints();
			constraints.gridX		=	1;
			constraints.gridY		=	1;
			constraints.gridHeight	=	1;
			constraints.gridWidth	=	1;
			constraints.vAlign		=	CellConstraints.TOP;
			
			panel.add(title.getPanel(), constraints);			
			
		}
		layout.appendRow(RowSpec.decode("pref"));
	}
	
	private void refreshTitle(){
		if (true) {
			JPanel panel = title.getPanel();
			CellConstraints constraints = layout.getConstraints(panel);
			this.panel.remove(panel);
			constraints.gridWidth = layout.getColumnCount();
			layout.removeRow(constraints.gridY);
			layout.insertRow(constraints.gridY, RowSpec.decode(panel.getHeight()+"px"));
			this.panel.add(panel, constraints);
			this.panel.invalidate();
			this.panel.revalidate();
			title.getPanel().repaint();
			
		}
	}

	@Override
	public List<? extends SubCategoryView> getEntries() {
		return new ArrayList<>(subCategoryViews);
	}

	@Override
	public void add(SubCategoryView entry) {
		SSubCategoryView view = (SSubCategoryView) entry;
		subCategoryViews.add(view);
		
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		
		layout.addGroupedColumn(layout.getColumnCount());
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	layout.getColumnCount();
		constraints.gridY		=	layout.getRowCount();
		constraints.hAlign		=	CellConstraints.FILL;
		constraints.vAlign		=	CellConstraints.TOP;
		
		panel.add(view.getPanel(), constraints);
		layout.appendColumn(ColumnSpec.decode("10px"));
		
		refreshTitle();
		
	}

	@Override
	public void remove(SubCategoryView entry) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void insert(int pos, SubCategoryView entry) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addListener(DataViewListener<SubCategoryView> listener) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public void removeListener(DataViewListener<SubCategoryView> listener) {
		throw new UnsupportedOperationException();
		
	}

	public JPanel getPanel() {
		return panel;
	}
	
	

}
