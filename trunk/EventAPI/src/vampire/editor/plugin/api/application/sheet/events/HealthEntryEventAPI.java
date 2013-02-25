package vampire.editor.plugin.api.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.HealthEntryControllerAPI;
import vampire.editor.plugin.api.domain.sheet.DamageType;

public interface HealthEntryEventAPI {
	
	public HealthEntryControllerAPI getSource();
	
	public int getFormerPenalty();
	
	public int getNewPenalty();
	
	public String getFormerText();
	
	public String getNewText();
	
	public DamageType getFormerDamageType();
	
	public DamageType getNewDamageType();
	
}
