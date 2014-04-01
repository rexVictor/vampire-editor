package vampire.editor.plugin.api.plugin.view.factories;

import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

public interface SubCategoryViewFactoryModule extends AbstractNonLeafViewFactoryModule<SubCategoryAPI, SubCategoryView, TraitView>{
	
	public void setManager(ManagerAPI manager);

}
