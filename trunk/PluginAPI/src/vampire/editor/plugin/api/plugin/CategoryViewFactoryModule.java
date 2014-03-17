package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.view.sheet.CategoryView;

public interface CategoryViewFactoryModule {
	
	public void process(CategoryAPI category, ModelToViewModelMapperAPI mapper, CategoryView categoryView);

}
