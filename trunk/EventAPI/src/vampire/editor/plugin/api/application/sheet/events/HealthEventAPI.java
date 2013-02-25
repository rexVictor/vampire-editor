package vampire.editor.plugin.api.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.HealthControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.HealthEntryControllerAPI;

public interface HealthEventAPI {
	
	public HealthControllerAPI getSource();
	
	public HealthEntryControllerAPI getAdded();
	
	public HealthEntryControllerAPI getRemoved();
	
}
