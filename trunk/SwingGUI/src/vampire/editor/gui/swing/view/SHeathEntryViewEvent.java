package vampire.editor.gui.swing.view;

import vampire.editor.plugin.api.domain.sheet.DamageType;
import vampire.editor.plugin.api.view.events.HealthViewEntryEvent;

public class SHeathEntryViewEvent implements HealthViewEntryEvent{
	
	private final String description;
	
	private final int penalty;
	
	private final DamageType damageType;

	public SHeathEntryViewEvent(String description, int penalty, DamageType damageType) {
		super();
		this.description = description;
		this.penalty = penalty;
		this.damageType = damageType;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int getPenalty() {
		return penalty;
	}

	@Override
	public DamageType getDamageType() {
		return damageType;
	};
	
	
	
	

}
