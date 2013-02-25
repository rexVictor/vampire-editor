package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.HealthEntryListener;
import vampire.editor.plugin.api.domain.sheet.DamageType;
import vampire.editor.plugin.api.domain.sheet.HealthEntryAPI;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;

public interface HealthEntryControllerAPI {
	
	public void setDamageType(DamageType damageType);
	
	public void setPenalty(int penatly);
	
	public void setText(String text);
	
	public void addListener(HealthEntryListener l);
	
	public void removeListener(HealthEntryListener l);
	
	public HealthEntryAPI getHealthEntry();
	
	public HealthEntryView getView();

}
