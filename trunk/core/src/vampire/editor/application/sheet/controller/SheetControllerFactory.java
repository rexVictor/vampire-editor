package vampire.editor.application.sheet.controller;

import java.util.List;

import vampire.editor.domain.sheet.BloodPool;
import vampire.editor.domain.sheet.Category;
import vampire.editor.domain.sheet.Data;
import vampire.editor.domain.sheet.Health;
import vampire.editor.domain.sheet.HealthEntry;
import vampire.editor.domain.sheet.Merit;
import vampire.editor.domain.sheet.Merits;
import vampire.editor.domain.sheet.Meta;
import vampire.editor.domain.sheet.MetaEntry;
import vampire.editor.domain.sheet.Sheet;
import vampire.editor.domain.sheet.SubCategory;
import vampire.editor.domain.sheet.Trait;
import vampire.editor.domain.sheet.Value;

import vampire.editor.plugin.api.application.sheet.controller.MiscControllerAPI;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.view.sheet.BloodPoolView;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;
import vampire.editor.plugin.api.view.sheet.HealthView;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;
import vampire.editor.plugin.api.view.sheet.MeritView;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;
import vampire.editor.plugin.api.view.sheet.MetaView;
import vampire.editor.plugin.api.view.sheet.MiscView;
import vampire.editor.plugin.api.view.sheet.SheetView;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;


public class SheetControllerFactory {
	
	public SheetController buildSheetController(VampireDocumentAPI document, SheetView view){
		Sheet sheet = (Sheet) document.getSheet();
		SheetController controller = new SheetController(document, view);
		
		Meta meta = sheet.getMeta();
		MetaView metaView = view.getMetaView();
		MetaController metaController = buildMetaController(meta, metaView);
		controller.setMetaController(metaController);
		
		Data<? extends Category> cats = sheet.getCategories();
		List<? extends CategoryView> catViews = view.getCategoryViews();
		int i = 0;
		
		for (Category cat : cats){
			controller.addCategoryController(buildCategoryController(cat, catViews.get(i)));
			i++;
		}
		
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
			controller.addMetaEntry0(entryController);
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
			controller.addMerit0(entryController);
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
			controller.addHealthEntry0(entryController);
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
			controller.addSubCategory(buildSubCategoryController(subCat, subCategoryViews.get(i)));
			i++;
		}
		return controller;
	}
	

	
	public SubCategoryController buildSubCategoryController(SubCategory subCategory, SubCategoryView view){
		SubCategoryController controller = new SubCategoryController(subCategory, view);
		List<? extends TraitView> traitViews = view.getEntries();
		int i = 0;
		for (Trait trait : subCategory){
			controller.addTrait0(buildTraitController(trait, traitViews.get(i)));
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
