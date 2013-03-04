package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vampire.editor.domain.sheet.Meta;
import vampire.editor.plugin.api.application.sheet.controller.MetaControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaListener;
import vampire.editor.plugin.api.domain.sheet.MetaAPI;
import vampire.editor.plugin.api.view.sheet.MetaView;

public class MetaController implements MetaControllerAPI{
	
	private final Meta meta;
	
	private final MetaView metaView;
	
	private final List<MetaEntryControllerAPI> metaEntryControllers = new ArrayList<>();
	
	private final Map<String, MetaEntryControllerAPI> metaEntryControllerMap = new HashMap<>();

	public MetaController(Meta meta, MetaView metaView) {
		super();
		this.meta = meta;
		this.metaView = metaView;
	}
	
	@Override
	public void addMetaEntry(MetaEntryControllerAPI controller) {
		
	}
	
	void addMetaEntry0(MetaEntryControllerAPI controller){
		metaEntryControllers.add(controller);
		String title = controller.getMetaEntry().getName();
		metaEntryControllerMap.put(title, controller);
	}
	
	@Override
	public MetaEntryControllerAPI getMetaEntryController(String key) {
		return metaEntryControllerMap.get(key);
	}

	@Override
	public MetaAPI getMeta() {
		return meta;
	}

	@Override
	public MetaView getView() {
		return metaView;
	}

	@Override
	public void addListener(MetaListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeListener(MetaListener l) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeMetaEntry(MetaEntryControllerAPI metaEntry) {
		// TODO Auto-generated method stub
		
	}
	
	

}
