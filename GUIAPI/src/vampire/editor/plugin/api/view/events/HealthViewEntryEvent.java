package vampire.editor.plugin.api.view.events;

import vampire.editor.plugin.api.domain.sheet.DamageType;

public interface HealthViewEntryEvent {
	
	public DamageType getDamageType();
	
	public int getPenalty();
	
	public String getDescription();

}
