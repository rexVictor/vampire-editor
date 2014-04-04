package vampire.editor.plugin.popupentryadder;

import java.util.Map;

import javax.swing.JPopupMenu;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.plugin.view.factories.SubCategoryViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;
import vampire.editor.plugin.api.view.sheet.TraitView;

public class Module implements SubCategoryViewFactoryModule{
	
	private final Map<String, JPopupMenu> map;

	public Module(Map<String, JPopupMenu> map) {
		super();
		this.map = map;
		
	}

	@Override
	public void processFinal(SubCategoryAPI subCategory,
			ModelToViewModelMapperAPI mapper, SubCategoryView traitView) {}

	@Override
	public void processInitial(SubCategoryAPI m,ModelToViewModelMapperAPI mapper) {}

	@Override
	public void addToChild(SubCategoryAPI m, ModelToViewModelMapperAPI mapper,
			TraitView subView) {
		subView.setPopupMenu(map.get(m.getName()));
	}

}
