package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.HealthEntryAPI;
import vampire.editor.plugin.api.domain.sheet.DamageType;

public interface IHealthEntry extends HealthEntryAPI {

	public void setPenalty(int penalty);

	public void setName(String name);

	public void setDamageType(DamageType damageType);
	
	@Override
	public IHealthEntry clone();

}