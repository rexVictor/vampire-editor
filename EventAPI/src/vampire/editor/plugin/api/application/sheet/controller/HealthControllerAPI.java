package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.HealthListener;
import vampire.editor.plugin.api.domain.sheet.HealthAPI;
import vampire.editor.plugin.api.view.sheet.HealthView;

public interface HealthControllerAPI {
	
	public HealthAPI getHealth();
	
	public HealthView getView();
	
	public void addHealthEntry(HealthEntryControllerAPI controller);
	
	public void removeHealthEntry(HealthEntryControllerAPI controller);
	
	public void addListener(HealthListener l);
	
	public void removeListener(HealthListener l);
}
