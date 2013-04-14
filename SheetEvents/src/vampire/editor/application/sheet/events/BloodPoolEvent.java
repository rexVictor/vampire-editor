package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.BloodPoolControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.BloodPoolEventAPI;

public class BloodPoolEvent implements BloodPoolEventAPI{
	
	private final BloodPoolControllerAPI source;
	
	private final int formerValue;
	
	private final int newValue;
	
	private final int formerMaxValue;
	
	private final int newMaxValue;
	
	

	public BloodPoolEvent(BloodPoolControllerAPI source, int formerValue,
			int newValue, int formerMaxValue, int newMaxValue) {
		super();
		this.source = source;
		this.formerValue = formerValue;
		this.newValue = newValue;
		this.formerMaxValue = formerMaxValue;
		this.newMaxValue = newMaxValue;
	}

	@Override
	public BloodPoolControllerAPI getSource() {
		return source;
	}

	@Override
	public int getFormerValue() {
		return formerValue;
	}

	@Override
	public int getNewValue() {
		return newValue;
	}

	@Override
	public int getFormerMaxValue() {
		return formerMaxValue;
	}

	@Override
	public int getNewMaxValue() {
		return newMaxValue;
	}
	
	

}
