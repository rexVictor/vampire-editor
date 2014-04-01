package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.SCategoryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.plugin.view.factories.CategoryViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.CategoryViewFactoryModule;
import vampire.editor.plugin.api.plugin.view.factories.SubCategoryViewFactory;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public class SCategoryViewFactory extends NonLeafAbstractFactory<CategoryAPI, SubCategoryAPI, SubCategoryView, 
												CategoryView, CategoryViewFactoryModule> implements CategoryViewFactory{
	
	public SCategoryViewFactory(ResourcesHolderAPI resources,
			DictionaryAPI dictionary,
			SubCategoryViewFactory subCategoryViewFactory) {
		super(subCategoryViewFactory, dictionary);
		add(new LineAdder(resources, dictionary));
	}

	@Override
	protected CategoryView constructView(Object viewAtts, CategoryAPI m) {
		return new SCategoryView();
	}

}
