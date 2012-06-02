package vampire.editor.application;

import java.util.List;

import vampire.editor.application.sheet.controller.CategoryController;
import vampire.editor.application.sheet.controller.SheetController;
import vampire.editor.application.sheet.controller.SubCategoryController;
import vampire.editor.application.sheet.controller.TraitController;
import vampire.editor.application.sheet.controller.ValueController;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.SheetView;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;
import vampire.editor.plugin.fullapi.sheet.ICategory;
import vampire.editor.plugin.fullapi.sheet.IData;
import vampire.editor.plugin.fullapi.sheet.ISheet;
import vampire.editor.plugin.fullapi.sheet.ISubCategory;
import vampire.editor.plugin.fullapi.sheet.ITrait;
import vampire.editor.plugin.fullapi.sheet.IValue;

public class SheetControllerFactory {
	
	public SheetController buildSheetController(ISheet sheet, SheetView view){
		
		SheetController controller = new SheetController(sheet, view);
		IData<? extends ICategory> cats = sheet.getCategories();
		List<? extends CategoryView> catViews = view.getCategoryViews();
		int i = 0;
		for (ICategory cat : cats){
			controller.addCategoryController(buildCategoryController(cat, catViews.get(i)));
			i++;
		}
		
		
		return controller;
	}
	
	
	public CategoryController buildCategoryController(ICategory category, CategoryView view){
		CategoryController controller = new CategoryController(category, view);
		List<? extends SubCategoryView> subCategoryViews = view.getEntries();
		int i = 0;
		for (ISubCategory subCat : category){
			controller.addSubCategory(buildSubCategoryController(subCat, subCategoryViews.get(i)));
			i++;
		}
		return controller;
	}
	

	
	public SubCategoryController buildSubCategoryController(ISubCategory subCategory, SubCategoryView view){
		SubCategoryController controller = new SubCategoryController(subCategory, view);
		List<? extends TraitView> traitViews = view.getEntries();
		int i = 0;
		for (ITrait trait : subCategory){
			controller.addTrait(buildTraitController(trait, traitViews.get(i)));
			i++;
		}
		return controller;
	}
	
	private TraitController buildTraitController(ITrait trait, TraitView view){
		ValueController valueController = buildValueController(trait.getValue(), view.getValueView());
		TraitController controller = new TraitController(valueController, trait, view);
		return controller;
	}
	
	private ValueController buildValueController(IValue value, ValueView view){
		ValueController controller = new ValueController(value, view);
		return controller;
	}

}
