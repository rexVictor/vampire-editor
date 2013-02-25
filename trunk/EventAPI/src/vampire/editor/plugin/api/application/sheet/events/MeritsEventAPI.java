package vampire.editor.plugin.api.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.MeritsControllerAPI;

public interface MeritsEventAPI {
	
	public MeritsControllerAPI getSource();
	
	public MeritEntryControllerAPI getAdded();
	
	public MeritEntryControllerAPI getRemoved();

}
