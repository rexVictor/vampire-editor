package vampire.editor.gui.swing.view;

import vampire.editor.plugin.api.view.events.BloodPoolViewEvent;

public class SBloodPoolViewEvent implements BloodPoolViewEvent{
	
	private final int value;
	
	private final int maxValue;
	
	

	public SBloodPoolViewEvent(int value, int maxValue) {
		super();
		this.value = value;
		this.maxValue = maxValue;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public int getMaxValue() {
		return maxValue;
	}
	
	

}
