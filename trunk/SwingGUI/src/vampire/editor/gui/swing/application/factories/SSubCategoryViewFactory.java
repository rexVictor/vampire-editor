package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.popupmenus.SubCategoryViewPopupMenu;
import vampire.editor.gui.swing.view.subcategoryviews.AbstractSubCategoryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.plugin.api.plugin.view.factories.SubCategoryViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.SubCategoryViewFactoryModule;
import vampire.editor.plugin.api.plugin.view.factories.TraitViewFactory;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

public class SSubCategoryViewFactory extends NonLeafAbstractFactory<SubCategoryAPI, TraitAPI, TraitView,
										SubCategoryView, SubCategoryViewFactoryModule>
										implements SubCategoryViewFactory{
	
	public SSubCategoryViewFactory(TraitViewFactory traitViewFactory,
			DictionaryAPI dictionary) {
		super(traitViewFactory, dictionary);
		add(new SubCategoryViewPopupMenu());
	}

	@Override
	protected SubCategoryView constructView(Object viewAtts, SubCategoryAPI m) {
		return AbstractSubCategoryView.buildSubCategoryView((SubCategoryViewAttributes) viewAtts, dictionary, m.getName());
	}

}
