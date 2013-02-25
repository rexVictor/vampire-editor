package vampire.editor.plugin.api.view.events;

public interface HealthEntryViewListener {
	
	public void damageTypeChanged(HealthViewEntryEvent event);
	
	public void penaltyChanged(HealthViewEntryEvent event);
	
	public void descriptionChanged(HealthViewEntryEvent event);

}
