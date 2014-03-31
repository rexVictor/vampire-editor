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
import java.util.LinkedList;
import java.util.List;

import vampire.editor.gui.swing.view.HorizontalHealthEntryView;
import vampire.editor.gui.swing.view.HorizontalHealthView;
import vampire.editor.gui.swing.view.MiscView;
import vampire.editor.gui.swing.view.SBloodPoolView;
import vampire.editor.gui.swing.view.SBorderView;
import vampire.editor.gui.swing.view.SSheetView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.BloodPoolAPI;
import vampire.editor.plugin.api.domain.sheet.CategoriesAPI;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.HealthAPI;
import vampire.editor.plugin.api.domain.sheet.HealthEntryAPI;
import vampire.editor.plugin.api.domain.sheet.MeritsAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.domain.sheet.view.BloodPoolViewAttributesAPI;
import vampire.editor.plugin.api.domain.sheet.view.HealthEntryViewAttributesAPI;
import vampire.editor.plugin.api.domain.sheet.view.HealthViewAttributesAPI;
import vampire.editor.plugin.api.plugin.CategoryViewFactory;
import vampire.editor.plugin.api.plugin.MeritEntryViewFactory;
import vampire.editor.plugin.api.plugin.MeritViewFactory;
import vampire.editor.plugin.api.plugin.MetaEntryViewFactory;
import vampire.editor.plugin.api.plugin.MetaViewFactory;
import vampire.editor.plugin.api.plugin.SubCategoryViewFactory;
import vampire.editor.plugin.api.plugin.TraitViewFactory;
import vampire.editor.plugin.api.plugin.ValueViewFactory;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.HealthView;
import vampire.editor.plugin.api.view.sheet.MeritView;
import vampire.editor.plugin.api.view.sheet.MetaView;

public class SheetViewFactory implements vampire.editor.plugin.api.plugin.SheetViewFactory{
	
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
		MiscView view = buildAdvantageView(sheet.getBloodPool(), 
				sheet.getHealth(), sheet.getMerits(), sheet.getFlaws(), mapper);
		sheetView.setMiscView(view);
		
		SBorderView borderView = new SBorderView(resources.getBorder(sheet.getBorderKey()), sheetView);
		sheetView.setBorderView(borderView);
		return sheetView;
	}
	
	private MiscView buildAdvantageView(
												BloodPoolAPI bloodPool, HealthAPI health,
												MeritsAPI merits, MeritsAPI flaws, ModelToViewModelMapperAPI mapper){
		SBloodPoolView bloodPoolView = buildBloodPoolView(bloodPool, mapper);
		HealthView healthView = buildHealthView(health, mapper);
		MeritView meritView = meritViewFactory.build(mapper, merits);
		MeritView flawView = meritViewFactory.build(mapper, flaws);
		MiscView view = new MiscView(bloodPoolView, healthView, meritView, flawView);
		
		return view;
	}
	
	private List<CategoryView> buildCategoryViews(CategoriesAPI categories, ModelToViewModelMapperAPI mapper){
		List<CategoryView> categoryViews = new ArrayList<>();
		for (Iterator<? extends CategoryAPI> i = categories.getIterator(); i.hasNext();) {
			CategoryAPI category = i.next();
			categoryViews.add(categoryViewFactory.build(mapper, category));
		}
		return categoryViews;
	}
	
	private SBloodPoolView buildBloodPoolView(BloodPoolAPI bloodPool, ModelToViewModelMapperAPI mapper){
		SBloodPoolView view = new SBloodPoolView((BloodPoolViewAttributesAPI) mapper.getViewAttributes(bloodPool), dictionary);
		view.setMaxValue(bloodPool.getMaxValue());
		view.setValue(bloodPool.getValue());
		return view;
	}
	
	private HealthView buildHealthView(HealthAPI health, ModelToViewModelMapperAPI mapper){
		HorizontalHealthView healthView = new HorizontalHealthView(dictionary, (HealthViewAttributesAPI) mapper.getViewAttributes(health));
		List<HorizontalHealthEntryView> entryViews = buildHealthEntryViews(health, mapper);
		for (HorizontalHealthEntryView view : entryViews){
			healthView.addHealthEntryView(view);
		}
		return healthView;
	}
	
	private List<HorizontalHealthEntryView> buildHealthEntryViews(HealthAPI healthEntries, ModelToViewModelMapperAPI mapper){
		List<HorizontalHealthEntryView> entryViews = new LinkedList<>();
		for (Iterator<? extends HealthEntryAPI> i = healthEntries.getIterator(); i.hasNext();){
			HealthEntryAPI entry = i.next();
			entryViews.add(buildHealthEntryView(entry, mapper));
		}
		return entryViews;
	}
	
	private HorizontalHealthEntryView buildHealthEntryView(HealthEntryAPI healthEntry, ModelToViewModelMapperAPI mapper){
		HorizontalHealthEntryView entryView = new HorizontalHealthEntryView(dictionary, (HealthEntryViewAttributesAPI) mapper.getViewAttributes(healthEntry));
		entryView.setDamageType(healthEntry.getDamageType());
		entryView.setDescription(healthEntry.getName());
		entryView.setPenalty(healthEntry.getPenalty());
		return entryView;
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
