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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.MetaView;
import vampire.editor.plugin.api.view.sheet.MiscView;
import vampire.editor.plugin.api.view.sheet.SheetView;

public class SSheetView implements SheetView{
	
	private final JPanel panel = new JPanel();
	
	private final List<SCategoryView> categoryViews = new ArrayList<>();
	
	private SBorderView borderView;
	
	public SBorderView getBorderView() {
		return borderView;
	}

	public void setBorderView(SBorderView borderView) {
		this.borderView = borderView;
	}

	private MetaView metaView;
	
	private SMiscView miscView;

	
	public SMiscView getMiscView() {
		return miscView;
	}

	public SSheetView(){
		initialize();
	}
	
	private void initialize(){
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
	}

	@Override
	public void add(CategoryView categoryView) {
		SCategoryView view = (SCategoryView) categoryView;
		categoryViews.add(view);
		Helper.setMaximumHeightToPreferredHeight(view.getPanel());
		panel.add(view.getPanel());
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
		Helper.setMaximumHeightToPreferredHeight(view.getPanel());
		panel.add(view.getPanel());
	}

	public void setMiscView(MiscView view){
		this.miscView = (SMiscView) view;
		panel.add(this.miscView.getPanel());
		panel.add(Box.createGlue());
	}

	@Override
	public MetaView getMetaView() {
		return metaView;
	}
	
	
	
	


}
