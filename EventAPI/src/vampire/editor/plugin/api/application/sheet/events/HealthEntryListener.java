package vampire.editor.plugin.api.application.sheet.events;

public interface HealthEntryListener {
	
	public void penaltyChanged(HealthEntryEventAPI e);
	
	public void textChanged(HealthEntryEventAPI e);
	
	public void damageTypeChanged(HealthEntryEventAPI e);

}
