package vampire.editor.gui.swing.view;

import vampire.editor.plugin.api.view.events.ValueViewEvent;

public class SValueViewEvent implements ValueViewEvent{
	
	private final int value;
	
	private final int tempValue;
	
	

	public SValueViewEvent(int value, int tempValue) {
		super();
		this.value = value;
		this.tempValue = tempValue;
	}

	@Override
	public int getValue() {
		return value;
	}

	@Override
	public int getTempValue() {
		return tempValue;
	}

}
