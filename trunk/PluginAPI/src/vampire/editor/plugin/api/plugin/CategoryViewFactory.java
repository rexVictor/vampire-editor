package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.CategoriesAPI;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.view.sheet.CategoryView;

public interface CategoryViewFactory extends AbstractViewsFactory<CategoriesAPI, CategoryAPI, 
												CategoryView, CategoryViewFactoryModule>{
	
}
