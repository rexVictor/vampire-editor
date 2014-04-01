package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.SCategoriesView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.CategoriesAPI;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.plugin.view.factories.CategoriesViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.CategoriesViewFactoryModule;
import vampire.editor.plugin.api.plugin.view.factories.CategoryViewFactory;
import vampire.editor.plugin.api.view.sheet.CategoriesView;
import vampire.editor.plugin.api.view.sheet.CategoryView;

public class SCategoriesViewFactory extends NonLeafAbstractFactory<CategoriesAPI, CategoryAPI, 
																	CategoryView, CategoriesView, 
																	CategoriesViewFactoryModule>
									implements CategoriesViewFactory{

	public SCategoriesViewFactory(CategoryViewFactory viewFactory, DictionaryAPI dictionary){
		super(viewFactory, dictionary);
	}

	@Override
	protected CategoriesView constructView(Object viewAtts, CategoriesAPI m) {
		return new SCategoriesView();
	}

}
