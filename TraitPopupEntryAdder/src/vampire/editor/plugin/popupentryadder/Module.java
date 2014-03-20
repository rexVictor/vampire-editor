package vampire.editor.plugin.popupentryadder;

import java.util.Map;

import javax.swing.JPopupMenu;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.plugin.SubCategoryViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

public class Module implements SubCategoryViewFactoryModule{
	
	private final Map<String, JPopupMenu> map;

	public Module(Map<String, JPopupMenu> map) {
		super();
		this.map = map;
		
	}

	@Override
	public void process(SubCategoryAPI subCategory,
			ModelToViewModelMapperAPI mapper, SubCategoryView traitView) {
		for (TraitView tv : traitView.getEntries()){
			tv.setPopupMenu(map.get(subCategory.getName()));
		}
	}

	@Override
	public void setManager(ManagerAPI manager) {
	}

}
