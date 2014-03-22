package vampire.editor.plugin.metapopupentryadder;

import java.util.Map;

import javax.swing.JPopupMenu;

import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.plugin.MetaEntryViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;

public class Module implements MetaEntryViewFactoryModule{
	
	private final Map<String, JPopupMenu> map;

	public Module(Map<String, JPopupMenu> map) {
		super();
		this.map = map;
		
	}

	@Override
	public void processInitial(MetaEntryAPI m, ModelToViewModelMapperAPI mapper) {}

	@Override
	public void processFinal(MetaEntryAPI m, ModelToViewModelMapperAPI mapper,
			MetaEntryView view) {
		String name = m.getName();
		view.setPopupMenu(map.get(name));
	}

	

}
