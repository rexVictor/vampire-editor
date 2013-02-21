package vampire.editor.application;

import java.util.List;

import vampire.editor.application.sheet.controller.CategoryController;
import vampire.editor.application.sheet.controller.MetaEntryController;
import vampire.editor.application.sheet.controller.SheetController;
import vampire.editor.application.sheet.controller.SubCategoryController;
import vampire.editor.application.sheet.controller.TraitController;
import vampire.editor.application.sheet.controller.ValueController;
import vampire.editor.domain.sheet.Category;
import vampire.editor.domain.sheet.Data;
import vampire.editor.domain.sheet.MetaEntry;
import vampire.editor.domain.sheet.Sheet;
import vampire.editor.domain.sheet.SubCategory;
import vampire.editor.domain.sheet.Trait;
import vampire.editor.domain.sheet.Value;

import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;
import vampire.editor.plugin.api.view.sheet.SheetView;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;


public class SheetControllerFactory {
	
	public SheetController buildSheetController(VampireDocumentAPI document, SheetView view){
		Sheet sheet = (Sheet) document.getSheet();
		SheetController controller = new SheetController(sheet, view);
		
		Data<? extends MetaEntry> metaEntries = sheet.getMeta();
		List<? extends MetaEntryView> metaEntryViews = view.getMetaViews();
		int i = 0;
		for (MetaEntry entry : metaEntries){
			controller.addMetaEntryController(buildMetaEntryController(entry, metaEntryViews.get(i)));
			i++;
		}
		Data<? extends Category> cats = sheet.getCategories();
		List<? extends CategoryView> catViews = view.getCategoryViews();
		i = 0;
		for (Category cat : cats){
			controller.addCategoryController(buildCategoryController(cat, catViews.get(i)));
			i++;
		}
		
		
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
			controller.addTrait(buildTraitController(trait, traitViews.get(i)));
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
