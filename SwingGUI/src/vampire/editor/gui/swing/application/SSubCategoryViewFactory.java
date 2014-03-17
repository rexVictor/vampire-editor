package vampire.editor.gui.swing.application;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import vampire.editor.gui.swing.view.subcategoryviews.AbstractSubCategoryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.plugin.SubCategoryViewFactory;
import vampire.editor.plugin.api.plugin.SubCategoryViewFactoryModule;
import vampire.editor.plugin.api.plugin.TraitViewFactory;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

public class SSubCategoryViewFactory implements SubCategoryViewFactory{
	
	private final List<SubCategoryViewFactoryModule> modules = new LinkedList<>();
	
	private final TraitViewFactory traitViewFactory;
	
	private final DictionaryAPI dictionary;
	
	
	public SSubCategoryViewFactory(TraitViewFactory traitViewFactory,
			DictionaryAPI dictionary) {
		super();
		this.traitViewFactory = traitViewFactory;
		this.dictionary = dictionary;
	}

	public void add(SubCategoryViewFactoryModule module){
		modules.add(module);
	}
	
	public SubCategoryView build(SubCategoryAPI subCategory, ModelToViewModelMapperAPI mapper){
		SubCategoryViewAttributes atts = (SubCategoryViewAttributes) mapper.getViewAttributes(subCategory);
		AbstractSubCategoryView subCategoryView = AbstractSubCategoryView.buildSubCategoryView(atts, dictionary, subCategory.getName());
		List<TraitView> traitViews = traitViewFactory.build(subCategory, mapper);
		for (TraitView traitView : traitViews){
			subCategoryView.add0(traitView);
		}
		for (SubCategoryViewFactoryModule m : modules){
			m.process(subCategory, mapper, subCategoryView, dictionary);
		}
		return subCategoryView;
	}
	
	public List<SubCategoryView> build(CategoryAPI category, ModelToViewModelMapperAPI mapper){
		List<SubCategoryView> subCategoryViews = new ArrayList<>();
		for (Iterator<? extends SubCategoryAPI> i = category.getIterator(); i.hasNext();) {
			SubCategoryAPI subCategory = i.next();
			subCategoryViews.add(build(subCategory, mapper));
		}
		return subCategoryViews;
	}

}
