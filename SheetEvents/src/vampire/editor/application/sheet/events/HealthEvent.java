package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.HealthControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.HealthEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.HealthEventAPI;

public class HealthEvent implements HealthEventAPI{
	
	private final HealthControllerAPI  controller;
	
	private final HealthEntryControllerAPI added;
	
	private final HealthEntryControllerAPI removed;

	public HealthEvent(HealthControllerAPI controller,
			HealthEntryControllerAPI added, HealthEntryControllerAPI removed) {
		super();
		this.controller = controller;
		this.added = added;
		this.removed = removed;
	}

	public HealthControllerAPI getSource() {
		return controller;
	}

	public HealthEntryControllerAPI getAdded() {
		return added;
	}

	public HealthEntryControllerAPI getRemoved() {
		return removed;
	}
	
	

}
