package vampire.editor.plugin.api.plugin;

import java.util.List;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public interface SubCategoryViewFactory extends AbstractViewsFactory<CategoryAPI, SubCategoryAPI,
													SubCategoryView, SubCategoryViewFactoryModule>{
	
	public List<SubCategoryView> buildList(CategoryAPI category, ModelToViewModelMapperAPI mapper);
	
}
