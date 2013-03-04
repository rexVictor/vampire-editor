package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.MetaListener;
import vampire.editor.plugin.api.domain.sheet.MetaAPI;
import vampire.editor.plugin.api.view.sheet.MetaView;

public interface MetaControllerAPI {
	
	public MetaAPI getMeta();
	
	public MetaView getView();
	
	public void addListener(MetaListener l);
	
	public void removeListener(MetaListener l);
	
	public void addMetaEntry(MetaEntryControllerAPI metaEntry);
	
	public void removeMetaEntry(MetaEntryControllerAPI metaEntry);
	
	public MetaEntryControllerAPI getMetaEntryController(String key);
}
