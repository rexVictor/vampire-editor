package vampire.editor.gui.swing.application;

import java.util.ArrayList;
import java.util.List;

import vampire.editor.gui.swing.view.SCategoryView;
import vampire.editor.gui.swing.view.SSheetView;
import vampire.editor.gui.swing.view.SSubCategoryView;
import vampire.editor.gui.swing.view.STraitView;
import vampire.editor.gui.swing.view.SValueView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.DataAPI;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.domain.sheet.ValueAPI;
import vampire.editor.plugin.fullapi.sheet.view.ICategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;

public class SheetViewFactory {
	
	private final DictionaryAPI dictionary;
	
	public SheetViewFactory(DictionaryAPI dictionary){
		this.dictionary = dictionary;
	}
	
	public SSheetView buildSheetView(SheetAPI sheet){
		SSheetView sheetView = new SSheetView();
		List<SCategoryView> categoryViews = buildCategoryViews(sheet.getCategories());
		for (SCategoryView categoryView : categoryViews){
			sheetView.add(categoryView);
		}
		return sheetView;
	}
	
	private List<SCategoryView> buildCategoryViews(DataAPI<? extends CategoryAPI> categories){
		List<SCategoryView> categoryViews = new ArrayList<>();
		for (CategoryAPI category : categories){
			categoryViews.add(buildCategoryView(category));
		}
		return categoryViews;
	}
	
	private SCategoryView buildCategoryView(CategoryAPI category){
		SCategoryView categoryView = new SCategoryView((ICategoryViewAttributes) category.getViewAtts(), dictionary);
		@SuppressWarnings("unchecked")
		List<SSubCategoryView> subCategoryViews = buildSubCategoryViews((DataAPI<? extends SubCategoryAPI>) category);
		for (SSubCategoryView subCategoryView : subCategoryViews){
			categoryView.add(subCategoryView);
		}
		return categoryView;
	}
	
	private List<SSubCategoryView> buildSubCategoryViews(DataAPI<? extends SubCategoryAPI> subCategories){
		List<SSubCategoryView> subCategoryViews = new ArrayList<>();
		for (SubCategoryAPI subCategory : subCategories){
			subCategoryViews.add(buildSubCategoryView(subCategory));
		}
		return subCategoryViews;
	}
	
	private SSubCategoryView buildSubCategoryView(SubCategoryAPI subCategory){
		ISubCategoryViewAttributes atts = (ISubCategoryViewAttributes) subCategory.getViewAtts();
		SSubCategoryView subCategoryView = new SSubCategoryView(atts, dictionary, subCategory.getName());
		@SuppressWarnings("unchecked")
		List<STraitView> traitViews = buildTraitViews((DataAPI<TraitAPI>) subCategory);
		for (STraitView traitView : traitViews){
			subCategoryView.add(traitView);
		}
		return subCategoryView;
	}
	
	private List<STraitView> buildTraitViews(DataAPI<? extends TraitAPI> traits){
		List<STraitView> traitViews = new ArrayList<>();
		for (TraitAPI trait : traits) {
			traitViews.add(buildTraitView(trait));
		}
		return traitViews;
	}
	
	private STraitView buildTraitView(TraitAPI trait){
		SValueView valueView = buildValueView(trait.getValue());
		STraitView traitView = new STraitView(valueView, dictionary, (ITraitViewAttributes) trait.getViewAtts());
		traitView.setName(trait.getName());
		return traitView;
	}
	
	private SValueView buildValueView(ValueAPI value){
		SValueView view = new SValueView((IValueViewAttributes) value.getViewAtts());
		view.setValue(value.getValue());
		view.setTempValue(value.getTempValue());
		return view;
	}

}
