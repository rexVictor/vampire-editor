package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.BloodPoolAPI;

public class BloodPool implements BloodPoolAPI {

	private int value;
	
	private int maxValue;

	@Override
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public int getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}
	
	
}
