package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.subcategoryviews.AbstractSubCategoryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.plugin.SubCategoryViewFactory;
import vampire.editor.plugin.api.plugin.SubCategoryViewFactoryModule;
import vampire.editor.plugin.api.plugin.TraitViewFactory;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

public class SSubCategoryViewFactory extends NonLeafAbstractFactory<SubCategoryAPI, TraitAPI, TraitView,
										SubCategoryView, SubCategoryViewFactoryModule>
										implements SubCategoryViewFactory{
	
	private final DictionaryAPI dictionary;
	
	
	public SSubCategoryViewFactory(TraitViewFactory traitViewFactory,
			DictionaryAPI dictionary) {
		super(traitViewFactory);
		this.dictionary = dictionary;
	}

	@Override
	protected SubCategoryView constructView(Object viewAtts, SubCategoryAPI m) {
		return AbstractSubCategoryView.buildSubCategoryView((SubCategoryViewAttributes) viewAtts, dictionary, m.getName());
	}

}
