package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public interface SubCategoryViewFactoryModule {
	
	public void process(SubCategoryAPI subCategory, ModelToViewModelMapperAPI mapper, SubCategoryView traitView,
			DictionaryAPI dictionary);
	
	public void setManager(ManagerAPI manager);

}
