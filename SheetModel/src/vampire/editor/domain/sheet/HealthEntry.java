package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.DamageType;
import vampire.editor.plugin.fullapi.sheet.IHealthEntry;

public class HealthEntry implements IHealthEntry {
	
	private int penalty;
	
	private String name;
	
	private DamageType damageType;
	
	

	public HealthEntry() {
		super();
	}

	public HealthEntry(int penalty, String name, DamageType damageType) {
		super();
		this.penalty = penalty;
		this.name = name;
		this.damageType = damageType;
	}

	@Override
	public int getPenalty() {
		return penalty;
	}

	@Override
	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public DamageType getDamageType() {
		return damageType;
	}

	@Override
	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}
	
	@Override
	public HealthEntry clone(){
		return new HealthEntry(penalty, name, damageType);
	}

}
