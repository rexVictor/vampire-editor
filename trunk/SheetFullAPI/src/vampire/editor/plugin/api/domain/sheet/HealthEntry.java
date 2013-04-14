package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.DamageType;

public interface HealthEntry extends HealthEntryAPI{

	public int getPenalty();

	public void setPenalty(int penalty);

	public String getName();

	public void setName(String name);

	public DamageType getDamageType();

	public void setDamageType(DamageType damageType);

	public HealthEntry clone();

}