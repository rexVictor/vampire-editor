package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.HealthEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.HealthEntryEventAPI;
import vampire.editor.plugin.api.domain.sheet.DamageType;

public class HealthEntryEvent implements HealthEntryEventAPI{
	
	private final HealthEntryControllerAPI source;
	
	private final int formerPenalty;
	
	private final int newPenalty;
	
	private final DamageType formerDamageType;
	
	private final DamageType newDamageType;
	
	private final String formerText;
	
	private final String newText;

	public HealthEntryEvent(HealthEntryControllerAPI source, int formerPenalty,
			int newPenalty, DamageType formerDamageType,
			DamageType newDamageType, String formerText, String newText) {
		super();
		this.source = source;
		this.formerPenalty = formerPenalty;
		this.newPenalty = newPenalty;
		this.formerDamageType = formerDamageType;
		this.newDamageType = newDamageType;
		this.formerText = formerText;
		this.newText = newText;
	}

	@Override
	public HealthEntryControllerAPI getSource() {
		return source;
	}

	@Override
	public int getFormerPenalty() {
		return formerPenalty;
	}

	@Override
	public int getNewPenalty() {
		return newPenalty;
	}

	@Override
	public DamageType getFormerDamageType() {
		return formerDamageType;
	}

	@Override
	public DamageType getNewDamageType() {
		return newDamageType;
	}

	@Override
	public String getFormerText() {
		return formerText;
	}

	@Override
	public String getNewText() {
		return newText;
	}
}
