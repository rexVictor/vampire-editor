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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import vampire.editor.plugin.api.view.sheet.CategoriesView;
import vampire.editor.plugin.api.view.sheet.MetaView;
import vampire.editor.plugin.api.view.sheet.MiscView;
import vampire.editor.plugin.api.view.sheet.SheetView;

public class SSheetView implements SheetView{
	
	private final JPanel panel = new JPanel();
	
	private SBorderView borderView;
	
	private MetaView metaView;

	private SMiscView miscView;
	
	private CategoriesView categoriesView;

	public SSheetView(){
		initialize();
	}

	public SBorderView getBorderView() {
		return borderView;
	}

	
	@Override
	public CategoriesView getCategoriesView() {
		return categoriesView;
	}

	@Override
	public MetaView getMetaView() {
		return metaView;
	}
	
	@Override
	public SMiscView getMiscView() {
		return miscView;
	}

	public JPanel getPanel() {
		return panel;
	}

	private void initialize(){
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
	}
	
	public void setBorderView(SBorderView borderView) {
		this.borderView = borderView;
	}

	public void setCategoriesView(CategoriesView categoriesView) {
		this.categoriesView = categoriesView;
		SCategoriesView view = (SCategoriesView) categoriesView;
		panel.add(view.getPanel(), 1);
	}

	public void setMetaView(MetaView metaView){
		SMetaView view = (SMetaView) metaView;
		this.metaView = metaView;
		Helper.setMaximumHeightToPreferredHeight(view.getPanel());
		panel.add(view.getPanel(),0);
	}

	public void setMiscView(MiscView view){
		this.miscView = (SMiscView) view;
		panel.add(this.miscView.getPanel());
		panel.add(Box.createGlue());
	}
	
	
	
	


}
