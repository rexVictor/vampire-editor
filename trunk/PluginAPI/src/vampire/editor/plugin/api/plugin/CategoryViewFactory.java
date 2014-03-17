package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.view.sheet.CategoryView;

public interface CategoryViewFactory {
	
	public void add(CategoryViewFactoryModule module);
	
	public CategoryView build(CategoryAPI category, ModelToViewModelMapperAPI mapper);
}
