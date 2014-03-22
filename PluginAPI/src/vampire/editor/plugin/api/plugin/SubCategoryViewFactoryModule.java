package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

public interface SubCategoryViewFactoryModule extends AbstractNonLeafViewFactoryModule<SubCategoryAPI, SubCategoryView, TraitView>{
	
	public void processFinal(SubCategoryAPI subCategory, ModelToViewModelMapperAPI mapper, SubCategoryView traitView);
	
	public void setManager(ManagerAPI manager);

}
