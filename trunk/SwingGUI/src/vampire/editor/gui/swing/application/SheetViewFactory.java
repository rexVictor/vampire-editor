package vampire.editor.gui.swing.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import vampire.editor.domain.sheet.view.*;
import vampire.editor.gui.swing.view.*;
import vampire.editor.gui.swing.view.valueviews.AbstractValueView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.*;
import vampire.editor.plugin.api.view.sheet.HealthView;

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
	
	private SMeritView buildMeritsView(MeritsAPI merits, ModelToViewModelMapperAPI mapper){
		SMeritView view = new SMeritView(merits.getName(), dictionary, mapper.getViewAttributes(merits));
		@SuppressWarnings("unchecked")
		List<SMeritEntryView> entryViews = buildMeritEntryViews((DataAPI<? extends MeritAPI>) merits, mapper);
		for (SMeritEntryView entryView : entryViews){
			view.addMeritEntryView(entryView);
		}
		return view;
	}
	
	private List<SMeritEntryView> buildMeritEntryViews(DataAPI<? extends MeritAPI> merits, ModelToViewModelMapperAPI mapper){
		List<SMeritEntryView> entryViews = new LinkedList<>();
		for (MeritAPI entry : merits){
			entryViews.add(buildMeritEntryView(entry, mapper));
		}
		return entryViews;
	}
	
	private SMeritEntryView buildMeritEntryView(MeritAPI merit, ModelToViewModelMapperAPI mapper){
		SMeritEntryView view = new SMeritEntryView(dictionary, mapper.getViewAttributes(merit));
		view.setCost(merit.getCost());
		view.setText(merit.getName());
		return view;
	}
	
	private SBloodPoolView buildBloodPoolView(BloodPoolAPI bloodPool, ModelToViewModelMapperAPI mapper){
		SBloodPoolView view = new SBloodPoolView(mapper.getViewAttributes(bloodPool), dictionary);
		view.setMaxValue(bloodPool.getMaxValue());
		view.setValue(bloodPool.getValue());
		return view;
	}
	
	private HealthView buildHealthView(HealthAPI health, ModelToViewModelMapperAPI mapper){
		HorizontalHealthView healthView = new HorizontalHealthView(dictionary, mapper.getViewAttributes(health));
		@SuppressWarnings("unchecked")
		List<HorizontalHealthEntryView> entryViews = buildHealthEntryViews((DataAPI<? extends HealthEntryAPI>) health, mapper);
		for (HorizontalHealthEntryView view : entryViews){
			healthView.addHealthEntryView(view);
		}
		return healthView;
	}
	
	private List<HorizontalHealthEntryView> buildHealthEntryViews(DataAPI<? extends HealthEntryAPI> healthEntries, ModelToViewModelMapperAPI mapper){
		List<HorizontalHealthEntryView> entryViews = new LinkedList<>();
		for (HealthEntryAPI entry : healthEntries){
			entryViews.add(buildHealthEntryView(entry, mapper));
		}
		return entryViews;
	}
	
	private HorizontalHealthEntryView buildHealthEntryView(HealthEntryAPI healthEntry, ModelToViewModelMapperAPI mapper){
		HorizontalHealthEntryView entryView = new HorizontalHealthEntryView(dictionary, mapper.getViewAttributes(healthEntry));
		entryView.setDamageType(healthEntry.getDamageType());
		entryView.setDescription(healthEntry.getName());
		entryView.setPenalty(healthEntry.getPenalty());
		return entryView;
	}

}
