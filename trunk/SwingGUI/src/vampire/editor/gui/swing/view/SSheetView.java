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

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.MetaView;
import vampire.editor.plugin.api.view.sheet.SheetView;

public class SSheetView implements SheetView{
	
	private final JPanel panel = new JPanel();
	
	private final FormLayout layout = new FormLayout();
	
	private final List<SCategoryView> categoryViews = new ArrayList<>();
	
	private SBorderView borderView;
	
	public SBorderView getBorderView() {
		return borderView;
	}

	public void setBorderView(SBorderView borderView) {
		this.borderView = borderView;
	}

	private MetaView metaView;
	
	private MiscView miscView;

	
	public MiscView getMiscView() {
		return miscView;
	}

	public SSheetView(){
		initialize();
	}
	
	private void initialize(){
		panel.setLayout(layout);
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		
	}

	@Override
	public void add(CategoryView categoryView) {
		SCategoryView view = (SCategoryView) categoryView;
		categoryViews.add(view);
		layout.appendRow(RowSpec.decode("pref"));
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	1;
		constraints.gridY		=	layout.getRowCount();
		constraints.hAlign		=	CellConstraints.FILL;
		
		panel.add(view.getPanel(), constraints);
		
		
	}

	public JPanel getPanel() {
		return panel;
	}

	@Override
	public List<SCategoryView> getCategoryViews() {
		return new ArrayList<>(categoryViews);
	}
	
	public void setMetaView(MetaView metaView){
		SMetaView view = (SMetaView) metaView;
		this.metaView = metaView;
		layout.appendRow(RowSpec.decode("pref"));
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	1;
		constraints.gridY		=	1;
		constraints.hAlign		=	CellConstraints.FILL;
		
		panel.add(view.getPanel(), constraints);
	}

	public void setMiscView(MiscView view){
		this.miscView = view;
		layout.appendRow(RowSpec.decode("pref"));
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	1;
		constraints.gridY		=	layout.getRowCount();
		constraints.hAlign		=	CellConstraints.FILL;
		panel.add(view.getPanel(), constraints);
		
	}

	@Override
	public MetaView getMetaView() {
		return metaView;
	}
	
	
	
	


}
