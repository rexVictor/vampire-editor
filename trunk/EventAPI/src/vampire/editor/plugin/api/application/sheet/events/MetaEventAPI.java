package vampire.editor.plugin.api.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.MetaControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;

public interface MetaEventAPI {
	
	public MetaControllerAPI getSource();
	
	public MetaEntryControllerAPI getAdded();
	
	public MetaEntryControllerAPI getRemoved();

}
