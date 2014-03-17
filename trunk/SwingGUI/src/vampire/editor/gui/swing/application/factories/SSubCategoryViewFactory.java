package vampire.editor.gui.swing.application.factories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import vampire.editor.gui.swing.view.subcategoryviews.AbstractSubCategoryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.plugin.SubCategoryViewFactory;
import vampire.editor.plugin.api.plugin.SubCategoryViewFactoryModule;
import vampire.editor.plugin.api.plugin.TraitViewFactory;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

public class SSubCategoryViewFactory extends AbstractFactory<SubCategoryAPI, TraitAPI, TraitView,
										SubCategoryView, SubCategoryViewFactoryModule>
										implements SubCategoryViewFactory{
	
	private final DictionaryAPI dictionary;
	
	
	public SSubCategoryViewFactory(TraitViewFactory traitViewFactory,
			DictionaryAPI dictionary) {
		super(traitViewFactory);
		this.dictionary = dictionary;
	}

	public List<SubCategoryView> buildList(CategoryAPI category, ModelToViewModelMapperAPI mapper){
		List<SubCategoryView> subCategoryViews = new ArrayList<>();
		for (Iterator<? extends SubCategoryAPI> i = category.getIterator(); i.hasNext();) {
			SubCategoryAPI subCategory = i.next();
			subCategoryViews.add(build(mapper, subCategory));
		}
		return subCategoryViews;
	}

	@Override
	protected SubCategoryView constructView(Object viewAtts, SubCategoryAPI m) {
		return AbstractSubCategoryView.buildSubCategoryView((SubCategoryViewAttributes) viewAtts, dictionary, m.getName());
	}

}
