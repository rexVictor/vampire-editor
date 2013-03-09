package vampire.editor.plugin.api.view.sheet;

import vampire.editor.plugin.api.domain.sheet.DamageType;
import vampire.editor.plugin.api.view.events.HealthEntryViewListener;

public interface HealthEntryView {
	
	public void addListener(HealthEntryViewListener listener);
	
	public void setDamageType(DamageType damageType);
	
	public void setPenalty(int penatly);
	
	public void setDescription(String description);

}