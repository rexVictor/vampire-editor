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
package vampire.editor.gui.swing.application.factories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vampire.editor.gui.swing.view.SBorderView;
import vampire.editor.gui.swing.view.SSheetView;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;

import vampire.editor.plugin.api.domain.sheet.CategoriesAPI;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;

import vampire.editor.plugin.api.plugin.view.factories.*;

import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.MetaView;
import vampire.editor.plugin.api.view.sheet.MiscView;

public class SheetViewFactory implements vampire.editor.plugin.api.plugin.view.factories.SheetViewFactory{
	
	private final DictionaryAPI dictionary;
	
	private final ResourcesHolderAPI resources;
	
	private final CategoryViewFactory categoryViewFactory;
	
	private final SubCategoryViewFactory subCategoryViewFactory;
	
	private final TraitViewFactory traitViewFactory;
	
	private final ValueViewFactory valueViewFactory;
	
	private final MetaEntryViewFactory metaEntryViewFactory;
	
	private final MetaViewFactory metaViewFactory;
	
	private final MeritEntryViewFactory meritEntryViewFactory;
	
	private final MeritViewFactory meritViewFactory;
	
	private final HealthViewFactory healthViewFactory;
	
	private final HealthEntryViewFactory healthEntryViewFactory;
	
	private final BloodpoolViewFactory bloodpoolViewFactory;
	
	private final MiscViewFactory miscViewFactory;
	
	public SheetViewFactory(ResourcesHolderAPI resources){
		this.dictionary = resources.getDictionary("sheet");
		this.resources = resources;
		valueViewFactory = new SValueViewFactory();
		traitViewFactory = new STraitViewFactory(dictionary, valueViewFactory);
		subCategoryViewFactory = new SSubCategoryViewFactory(traitViewFactory, dictionary);
		categoryViewFactory = new SCategoryViewFactory(resources, dictionary, subCategoryViewFactory);
		metaEntryViewFactory = new SMetaEntryViewFactory(dictionary);
		metaViewFactory = new SMetaViewFactory(metaEntryViewFactory);
		meritEntryViewFactory = new SMeritEntryViewFactory(dictionary);
		meritViewFactory = new SMeritViewFactory(meritEntryViewFactory, dictionary);
		healthEntryViewFactory = new SHealthEntryViewFactory(dictionary);
		healthViewFactory = new SHealthViewFactory(healthEntryViewFactory, dictionary);
		bloodpoolViewFactory = new SBloodpoolViewFactory(dictionary);
		miscViewFactory = new SMiscViewFactory(bloodpoolViewFactory, healthViewFactory, meritViewFactory);
	}
	
	@Override
	public SSheetView buildSheetView(VampireDocumentAPI document){
		SheetAPI sheet = document.getSheet();
		ModelToViewModelMapperAPI mapper = document.getModelToViewModelMapper();
		SSheetView sheetView = new SSheetView();
		MetaView metaView = metaViewFactory.build(mapper, sheet.getMeta());
		sheetView.setMetaView(metaView);
		List<CategoryView> categoryViews = buildCategoryViews(sheet.getCategories(), mapper);
		for (CategoryView categoryView : categoryViews){
			sheetView.add(categoryView);
		}
		MiscView view = miscViewFactory.build(mapper, sheet.getBloodPool(), 
				sheet.getHealth(), sheet.getMerits(), sheet.getFlaws());
		sheetView.setMiscView(view);
		
		SBorderView borderView = new SBorderView(resources.getBorder(sheet.getBorderKey()), sheetView);
		sheetView.setBorderView(borderView);
		return sheetView;
	}
	
	private List<CategoryView> buildCategoryViews(CategoriesAPI categories, ModelToViewModelMapperAPI mapper){
		List<CategoryView> categoryViews = new ArrayList<>();
		for (Iterator<? extends CategoryAPI> i = categories.getIterator(); i.hasNext();) {
			CategoryAPI category = i.next();
			categoryViews.add(categoryViewFactory.build(mapper, category));
		}
		return categoryViews;
	}
	
	public SubCategoryViewFactory getSubCategoryViewFactory(){
		return subCategoryViewFactory;
	}

	public CategoryViewFactory getCategoryViewFactory() {
		return categoryViewFactory;
	}

	public TraitViewFactory getTraitViewFactory() {
		return traitViewFactory;
	}

	public ValueViewFactory getValueViewFactory() {
		return valueViewFactory;
	}

	public MetaEntryViewFactory getMetaEntryViewFactory() {
		return metaEntryViewFactory;
	}

	public MetaViewFactory getMetaViewFactory() {
		return metaViewFactory;
	}

	public MeritEntryViewFactory getMeritEntryViewFactory() {
		return meritEntryViewFactory;
	}

	public MeritViewFactory getMeritViewFactory() {
		return meritViewFactory;
	}
}
