package vampire.editor.plugin.api.plugin;

import java.util.List;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public interface SubCategoryViewFactory {
	
	public SubCategoryView build(SubCategoryAPI subCategory, ModelToViewModelMapperAPI mapper);
	
	public List<SubCategoryView> build(CategoryAPI category, ModelToViewModelMapperAPI mapper);
	
	public void add(SubCategoryViewFactoryModule module);
	

}
