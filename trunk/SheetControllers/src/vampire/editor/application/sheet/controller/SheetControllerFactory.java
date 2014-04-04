/*******************************************************************************
 * Vampire Editor SheetControllers.
 * Copyright (C) 2014  Matthias Johannes Reimchen
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
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.application.sheet.controller;

import java.util.List;

import vampire.editor.plugin.api.application.sheet.controller.MiscControllerAPI;
import vampire.editor.plugin.api.domain.sheet.*;
import vampire.editor.plugin.api.view.sheet.*;

@SuppressWarnings("static-method")
public class SheetControllerFactory {
	
	public SheetController buildSheetController(VampireDocumentAPI document, SheetView view){
		Sheet sheet = (Sheet) document.getSheet();
		SheetController controller = new SheetController(document, view);
		
		Meta meta = sheet.getMeta();
		MetaView metaView = view.getMetaView();
		MetaController metaController = buildMetaController(meta, metaView);
		controller.setMetaController(metaController);
		
		Categories cats = sheet.getCategories();
		List<? extends CategoryView> catViews = view.getCategoriesView().getCategoryViews();
		int i = 0;
		CategoriesController categoriesController = new CategoriesController();
		for (Category cat : cats){
			categoriesController.addCategoryController(buildCategoryController(cat, catViews.get(i)));
			i++;
		}
		
		controller.setCategoriesController(categoriesController);
		
		Health health = sheet.getHealth();
		Merits merits = sheet.getMerits();
		Merits flaws = sheet.getFlaws();
		BloodPool bloodPool = sheet.getBloodPool();
		MiscView miscView = view.getMiscView();
		
		MiscControllerAPI miscController = buildMiscController(merits, flaws, bloodPool, health, miscView);
		controller.setMiscController(miscController);
		
		
		return controller;
	}
	
	public MetaController buildMetaController(Meta meta, MetaView view){
		MetaController controller = new MetaController(meta, view);
		List<? extends MetaEntryView> metaEntryViews = view.getEntries();
		for (int i = 0; i < meta.size(); i++){
			MetaEntryController entryController = buildMetaEntryController(meta.get(i), metaEntryViews.get(i));
			controller.add0(entryController);
		}
		return controller;
	}
	
	public MiscControllerAPI buildMiscController(Merits merits, Merits flaws, BloodPool bloodPool,
			Health health, MiscView view){
		MeritView meritView = view.getMeritsView();
		MeritView flawView = view.getFlawsView();
		BloodPoolView bloodPoolView = view.getBloodPoolView();
		HealthView healthView = view.getHealthView();
		MeritsController meritsController = buildMeritsController(merits, meritView);
		MeritsController flawsController = buildMeritsController(flaws, flawView);
		BloodPoolController bloodPoolController = buildBloodPoolController(bloodPool, bloodPoolView);
		HealthController healthController = buildHealthController(health, healthView);
		return new MiscController(meritsController, flawsController, healthController, bloodPoolController, view);
	}
	
	
	public MeritsController buildMeritsController(Merits merits, MeritView view){
		MeritsController controller = new MeritsController(merits, view);
		List<MeritEntryView> entryViews = view.getEntries();
		for (int i = 0; i < merits.size(); i++){
			Merit merit = merits.get(i);
			MeritEntryView entryView = entryViews.get(i);
			MeritEntryController entryController = buildMeritEntryController(merit, entryView);
			controller.add0(entryController);
		}
		return controller;
	}
	
	public MeritEntryController buildMeritEntryController(Merit merit, MeritEntryView view){
		MeritEntryController controller = new MeritEntryController(merit, view);
		return controller;
	}
	
	public BloodPoolController buildBloodPoolController(BloodPool bloodPool, BloodPoolView view){
		BloodPoolController controller = new BloodPoolController(bloodPool, view);
		return controller;
	}
	
	public HealthController buildHealthController(Health health, HealthView view){
		HealthController controller = new HealthController(health, view);
		List<HealthEntryView> entryViews = view.getEntries();
		for (int i = 0; i < health.size(); i++){
			HealthEntry entry = health.get(i);
			HealthEntryView entryView = entryViews.get(i);
			HealthEntryController entryController = buildHealthEntryController(entry, entryView);
			controller.add0(entryController);
		}
		return controller;
	}
	
	public HealthEntryController buildHealthEntryController(HealthEntry entry, HealthEntryView view){
		HealthEntryController controller = new HealthEntryController(entry, view);
		return controller;
	}
	
	public MetaEntryController buildMetaEntryController(MetaEntry entry, MetaEntryView view){
		MetaEntryController controller = new MetaEntryController(entry, view);
		return controller;
	}
	
	
	public CategoryController buildCategoryController(Category category, CategoryView view){
		CategoryController controller = new CategoryController(category, view);
		List<? extends SubCategoryView> subCategoryViews = view.getEntries();
		int i = 0;
		for (SubCategory subCat : category){
			controller.add0(buildSubCategoryController(subCat, subCategoryViews.get(i)));
			i++;
		}
		return controller;
	}
	

	
	public SubCategoryController buildSubCategoryController(SubCategory subCategory, SubCategoryView view){
		SubCategoryController controller = new SubCategoryController(subCategory, view);
		List<? extends TraitView> traitViews = view.getEntries();
		int i = 0;
		for (Trait trait : subCategory){
			controller.add0(buildTraitController(trait, traitViews.get(i)));
			i++;
		}
		return controller;
	}
	
	private TraitController buildTraitController(Trait trait, TraitView view){
		ValueController valueController = buildValueController(trait.getValue(), view.getValueView());
		TraitController controller = new TraitController(valueController, trait, view);
		return controller;
	}
	
	
	private ValueController buildValueController(Value value, ValueView view){
		ValueController controller = new ValueController(value, view);
		return controller;
	}

}
