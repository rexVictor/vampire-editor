package vampire.editor.application.sheet.events;

import vampire.editor.application.sheet.controller.BloodPoolController;
import vampire.editor.plugin.api.application.sheet.events.BloodPoolEventAPI;

public class BloodPoolEvent implements BloodPoolEventAPI{
	
	private final BloodPoolController source;
	
	private final int formerValue;
	
	private final int newValue;
	
	private final int formerMaxValue;
	
	private final int newMaxValue;
	
	

	public BloodPoolEvent(BloodPoolController source, int formerValue,
			int newValue, int formerMaxValue, int newMaxValue) {
		super();
		this.source = source;
		this.formerValue = formerValue;
		this.newValue = newValue;
		this.formerMaxValue = formerMaxValue;
		this.newMaxValue = newMaxValue;
	}

	@Override
	public BloodPoolController getSource() {
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
