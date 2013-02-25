package vampire.editor.plugin.api.application.sheet.events;

public interface HealthListener {
	
	public void healthEntryAdded(HealthEventAPI e);
	
	public void healthEntryRemoved(HealthEventAPI e);

}
