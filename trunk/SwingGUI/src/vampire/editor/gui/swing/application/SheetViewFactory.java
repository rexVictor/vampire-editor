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
package vampire.editor.gui.swing.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import vampire.editor.gui.swing.view.*;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.*;
import vampire.editor.plugin.api.domain.sheet.view.*;
import vampire.editor.plugin.api.plugin.CategoryViewFactory;
import vampire.editor.plugin.api.plugin.SubCategoryViewFactory;
import vampire.editor.plugin.api.plugin.TraitViewFactory;
import vampire.editor.plugin.api.plugin.ValueViewFactory;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.HealthView;

public class SheetViewFactory implements vampire.editor.plugin.api.plugin.SheetViewFactory{
	
	private final DictionaryAPI dictionary;
	
	private final ResourcesHolderAPI resources;
	
	private final CategoryViewFactory categoryViewFactory;
	
	private final SubCategoryViewFactory subCategoryViewFactory;
	
	private final TraitViewFactory traitViewFactory;
	
	private final ValueViewFactory valueViewFactory;
	
	public SheetViewFactory(ResourcesHolderAPI resources){
		this.dictionary = resources.getDictionary("sheet");
		this.resources = resources;
		valueViewFactory = new SValueViewFactory();
		traitViewFactory = new STraitViewFactory(dictionary, valueViewFactory);
		subCategoryViewFactory = new SSubCategoryViewFactory(traitViewFactory, dictionary);
		categoryViewFactory = new SCategoryViewFactory(resources, dictionary, subCategoryViewFactory);
	}
	
	@Override
	public SSheetView buildSheetView(VampireDocumentAPI document){
		SheetAPI sheet = document.getSheet();
		ModelToViewModelMapperAPI mapper = document.getModelToViewModelMapper();
		SSheetView sheetView = new SSheetView();
		SMetaView metaView = buildMetaView(sheet.getMeta(), mapper);
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
		SMeritView meritView = buildMeritsView(merits, mapper);
		SMeritView flawView = buildMeritsView(flaws, mapper);
		MiscView view = new MiscView(bloodPoolView, healthView, meritView, flawView);
		
		return view;
	}
	
	private SMetaView buildMetaView(MetaAPI meta, ModelToViewModelMapperAPI mapper){
		SMetaView view = new SMetaView();
		for (Iterator<? extends MetaEntryAPI> i = meta.getIterator();i.hasNext();){
			view.add(buildMetaEntryView(i.next(), mapper));
		}
		return view;
	}
	
	private SMetaEntryView buildMetaEntryView(MetaEntryAPI meta, ModelToViewModelMapperAPI mapper){
		SMetaEntryView view = new SMetaEntryView(dictionary, (MetaEntryViewAttributesAPI) mapper.getViewAttributes(meta));
		view.setTitle(meta.getName());
		view.setContent(meta.getValue());
		return view;
	}
	
	private List<CategoryView> buildCategoryViews(CategoriesAPI categories, ModelToViewModelMapperAPI mapper){
		List<CategoryView> categoryViews = new ArrayList<>();
		for (Iterator<? extends CategoryAPI> i = categories.getIterator(); i.hasNext();) {
			CategoryAPI category = i.next();
			categoryViews.add(categoryViewFactory.build(category, mapper));
		}
		return categoryViews;
	}
	
	private SMeritView buildMeritsView(MeritsAPI merits, ModelToViewModelMapperAPI mapper){
		SMeritView view = new SMeritView(merits.getName(), dictionary, (MeritViewAttributesAPI) mapper.getViewAttributes(merits));
		List<SMeritEntryView> entryViews = buildMeritEntryViews(merits, mapper);
		for (SMeritEntryView entryView : entryViews){
			view.addMeritEntryView(entryView);
		}
		return view;
	}
	
	private List<SMeritEntryView> buildMeritEntryViews(MeritsAPI merits, ModelToViewModelMapperAPI mapper){
		List<SMeritEntryView> entryViews = new LinkedList<>();
		for (Iterator<? extends MeritAPI> i = merits.getIterator(); i.hasNext();){
			MeritAPI entry = i.next();
			entryViews.add(buildMeritEntryView(entry, mapper));
		}
		return entryViews;
	}
	
	private SMeritEntryView buildMeritEntryView(MeritAPI merit, ModelToViewModelMapperAPI mapper){
		SMeritEntryView view = new SMeritEntryView(dictionary, (MeritEntryViewAttibutesAPI) mapper.getViewAttributes(merit));
		view.setCost(merit.getCost());
		view.setText(merit.getName());
		return view;
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

}
