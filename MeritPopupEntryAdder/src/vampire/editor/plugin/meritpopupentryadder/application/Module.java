package vampire.editor.plugin.meritpopupentryadder.application;

import java.util.Map;

import javax.swing.JPopupMenu;

import vampire.editor.plugin.api.domain.sheet.MeritsAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.plugin.view.factories.MeritViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;
import vampire.editor.plugin.api.view.sheet.MeritView;

public class Module implements MeritViewFactoryModule{
	
	private final Map<String, JPopupMenu> map;

	public Module(Map<String, JPopupMenu> map) {
		super();
		this.map = map;
	}

	@Override
	public void addToChild(MeritsAPI m, ModelToViewModelMapperAPI mapper,
			MeritEntryView subView) {
		subView.setPopupMenu(map.get(m.getName()));
		
	}

	@Override
	public void processInitial(MeritsAPI m, ModelToViewModelMapperAPI mapper) {}

	@Override
	public void processFinal(MeritsAPI m, ModelToViewModelMapperAPI mapper,
			MeritView view) {}

}
