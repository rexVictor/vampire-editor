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
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import vampire.editor.gui.swing.view.subcategoryviews.AbstractSubCategoryView;
import vampire.editor.plugin.api.view.events.DataViewListener;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public class SCategoryView implements CategoryView{
	
	private final JPanel panel = new JPanel();
	
	private final List<AbstractSubCategoryView> subCategoryViews = new ArrayList<>();
	
	private final JPanel subCategoriesPanel = new JPanel();
	
	public SCategoryView() {
		super();
		initialize();
	}
	
	private void initializeSubcategoriesPanel(){
		subCategoriesPanel.setLayout(new GridLayout(1, 0, 10, 0));
		subCategoriesPanel.setBackground(Color.WHITE);
		subCategoriesPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
	}
	
	private void initialize(){
		initializeSubcategoriesPanel();
		panel.add(subCategoriesPanel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setBackground(Color.WHITE);
	}
	

	@Override
	public List<? extends SubCategoryView> getEntries() {
		return new ArrayList<>(subCategoryViews);
	}

	@Override
	public void add(SubCategoryView entry) {
		AbstractSubCategoryView view = (AbstractSubCategoryView) entry;
		subCategoryViews.add(view);
		view.getPanel().setAlignmentX(JPanel.TOP_ALIGNMENT);
		view.getPanel().setAlignmentY(JPanel.TOP_ALIGNMENT);
		subCategoriesPanel.add(view.getPanel());
		
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
	
	public void addLine(LineImage lineImage){
		panel.add(lineImage, 0);
	}

	public JPanel getPanel() {
		return panel;
	}
	
	

}
