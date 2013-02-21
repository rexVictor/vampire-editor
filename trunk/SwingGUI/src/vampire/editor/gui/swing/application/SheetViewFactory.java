package vampire.editor.gui.swing.application;

import java.util.ArrayList;
import java.util.List;

import vampire.editor.domain.sheet.view.*;
import vampire.editor.gui.swing.view.*;
import vampire.editor.gui.swing.view.valueviews.AbstractValueView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.*;

public class SheetViewFactory implements vampire.editor.plugin.api.plugin.SheetViewFactory{
	
	private final DictionaryAPI dictionary;
	
	private final ResourcesHolderAPI resources;
	
	public SheetViewFactory(ResourcesHolderAPI resources){
		this.dictionary = resources.getDictionary("sheet");
		this.resources = resources;
	}
	
	@Override
	public SSheetView buildSheetView(VampireDocumentAPI document){
		SheetAPI sheet = document.getSheet();
		ModelToViewModelMapperAPI mapper = document.getModelToViewModelMapper();
		SSheetView sheetView = new SSheetView();
		SMetaView metaView = buildMetaView(sheet.getMeta(), mapper);
		sheetView.setMetaView(metaView);
		List<SCategoryView> categoryViews = buildCategoryViews(sheet.getCategories(), mapper);
		for (SCategoryView categoryView : categoryViews){
			sheetView.add(categoryView);
		}
		return sheetView;
	}
	
	private SMetaView buildMetaView(DataAPI<? extends MetaEntryAPI> metas, ModelToViewModelMapperAPI mapper){
		SMetaView view = new SMetaView();
		for (MetaEntryAPI m : metas){
			view.add(buildMetaEntryView(m, mapper));
		}
		return view;
	}
	
	private SMetaEntryView buildMetaEntryView(MetaEntryAPI meta, ModelToViewModelMapperAPI mapper){
		SMetaEntryView view = new SMetaEntryView(dictionary, mapper.getViewAttributes(meta));
		view.setTitle(meta.getName());
		view.setContent(meta.getValue());
		return view;
	}
	
	private List<SCategoryView> buildCategoryViews(DataAPI<? extends CategoryAPI> categories, ModelToViewModelMapperAPI mapper){
		List<SCategoryView> categoryViews = new ArrayList<>();
		for (CategoryAPI category : categories){
			categoryViews.add(buildCategoryView(category, mapper));
		}
		return categoryViews;
	}
	
	private SCategoryView buildCategoryView(CategoryAPI category, ModelToViewModelMapperAPI mapper){
		SCategoryView categoryView =
				new SCategoryView((CategoryViewAttributes) mapper.getViewAttributes(category), resources, category.getName());
		@SuppressWarnings("unchecked")
		List<SSubCategoryView> subCategoryViews = buildSubCategoryViews((DataAPI<? extends SubCategoryAPI>) category, mapper);
		for (SSubCategoryView subCategoryView : subCategoryViews){
			categoryView.add(subCategoryView);
		}
		return categoryView;
	}
	
	private List<SSubCategoryView> buildSubCategoryViews(DataAPI<? extends SubCategoryAPI> subCategories,
				ModelToViewModelMapperAPI mapper){
		List<SSubCategoryView> subCategoryViews = new ArrayList<>();
		for (SubCategoryAPI subCategory : subCategories){
			subCategoryViews.add(buildSubCategoryView(subCategory, mapper));
		}
		return subCategoryViews;
	}
	
	private SSubCategoryView buildSubCategoryView(SubCategoryAPI subCategory, ModelToViewModelMapperAPI mapper){
		SubCategoryViewAttributes atts = (SubCategoryViewAttributes) mapper.getViewAttributes(subCategory);
		SSubCategoryView subCategoryView = new SSubCategoryView(atts, dictionary, subCategory.getName());
		@SuppressWarnings("unchecked")
		List<STraitView> traitViews = buildTraitViews((DataAPI<TraitAPI>) subCategory, mapper);
		for (STraitView traitView : traitViews){
			subCategoryView.add(traitView);
		}
		return subCategoryView;
	}
	
	private List<STraitView> buildTraitViews(DataAPI<? extends TraitAPI> traits, ModelToViewModelMapperAPI mapper){
		List<STraitView> traitViews = new ArrayList<>();
		for (TraitAPI trait : traits) {
			traitViews.add(buildTraitView(trait, mapper));
		}
		return traitViews;
	}
	
	private STraitView buildTraitView(TraitAPI trait, ModelToViewModelMapperAPI mapper){
		AbstractValueView valueView = buildValueView(trait.getValue(), mapper);
		STraitView traitView = new STraitView(valueView, dictionary, (TraitViewAttributes) mapper.getViewAttributes(trait));
		traitView.setName(trait.getName());
		return traitView;
	}
	
	private AbstractValueView buildValueView(ValueAPI value, ModelToViewModelMapperAPI mapper){
		AbstractValueView view = 
				AbstractValueView.getValueView((
						(ValueViewAttributes) mapper.getViewAttributes(value)));
		view.setValue(value.getValue());
		view.setTempValue(value.getTempValue());
		return view;
	}

}
