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


import vampire.editor.gui.swing.view.SBorderView;
import vampire.editor.gui.swing.view.SSheetView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.plugin.view.factories.*;
import vampire.editor.plugin.api.view.sheet.CategoriesView;
import vampire.editor.plugin.api.view.sheet.MetaView;
import vampire.editor.plugin.api.view.sheet.MiscView;

public class SSheetViewFactory implements SheetViewFactory{
	
	private final DictionaryAPI dictionary;
	
	private final ResourcesHolderAPI resources;
	
	private final CategoriesViewFactory categoriesViewFactory;
	
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
	
	public SSheetViewFactory(ResourcesHolderAPI resources){
		this.dictionary = resources.getDictionary(DictionaryAPI.SHEET_DICTIONARY);
		this.resources = resources;
		valueViewFactory = new SValueViewFactory();
		traitViewFactory = new STraitViewFactory(dictionary, valueViewFactory);
		subCategoryViewFactory = new SSubCategoryViewFactory(traitViewFactory, dictionary);
		categoryViewFactory = new SCategoryViewFactory(resources, dictionary, subCategoryViewFactory);
		categoriesViewFactory = new SCategoriesViewFactory(categoryViewFactory, dictionary);
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
		CategoriesView categoriesView = categoriesViewFactory.build(mapper, sheet.getCategories());
		sheetView.setCategoriesView(categoriesView);
		MiscView view = miscViewFactory.build(mapper, sheet.getBloodPool(), 
				sheet.getHealth(), sheet.getMerits(), sheet.getFlaws());
		sheetView.setMiscView(view);
		
		SBorderView borderView = new SBorderView(resources.getBorder(sheet.getBorderKey()), sheetView);
		sheetView.setBorderView(borderView);
		return sheetView;
	}
	
	@Override
	public SubCategoryViewFactory getSubCategoryViewFactory(){
		return subCategoryViewFactory;
	}

	@Override
	public CategoryViewFactory getCategoryViewFactory() {
		return categoryViewFactory;
	}

	@Override
	public TraitViewFactory getTraitViewFactory() {
		return traitViewFactory;
	}

	@Override
	public ValueViewFactory getValueViewFactory() {
		return valueViewFactory;
	}

	@Override
	public MetaEntryViewFactory getMetaEntryViewFactory() {
		return metaEntryViewFactory;
	}

	@Override
	public MetaViewFactory getMetaViewFactory() {
		return metaViewFactory;
	}

	@Override
	public MeritEntryViewFactory getMeritEntryViewFactory() {
		return meritEntryViewFactory;
	}

	@Override
	public MeritViewFactory getMeritViewFactory() {
		return meritViewFactory;
	}

}
