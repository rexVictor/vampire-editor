package vampire.editor.gui.swing.application.factories;

import java.util.List;

import vampire.editor.gui.swing.view.SCategoryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.CategoriesAPI;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.plugin.CategoryViewFactory;
import vampire.editor.plugin.api.plugin.CategoryViewFactoryModule;
import vampire.editor.plugin.api.plugin.SubCategoryViewFactory;
import vampire.editor.plugin.api.view.sheet.CategoryView;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public class SCategoryViewFactory extends AbstractFactory<CategoryAPI, SubCategoryAPI, SubCategoryView, 
												CategoryView, CategoryViewFactoryModule> implements CategoryViewFactory{
	
	public SCategoryViewFactory(ResourcesHolderAPI resources,
			DictionaryAPI dictionary,
			SubCategoryViewFactory subCategoryViewFactory) {
		super(subCategoryViewFactory);
		add(new LineAdder(resources, dictionary));
	}

	@Override
	public List<CategoryView> buildList(CategoriesAPI categories, ModelToViewModelMapperAPI mapper){
		return null;
	}

	@Override
	protected CategoryView constructView(Object viewAtts, CategoryAPI m) {
		return new SCategoryView();
	}

}
