package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public interface SubCategoryViewFactoryModule extends AbstractViewFactoryModule<SubCategoryAPI, SubCategoryView>{
	
	public void process(SubCategoryAPI subCategory, ModelToViewModelMapperAPI mapper, SubCategoryView traitView);
	
	public void setManager(ManagerAPI manager);

}
