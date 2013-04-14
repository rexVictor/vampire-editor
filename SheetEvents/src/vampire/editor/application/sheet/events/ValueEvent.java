package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueEventAPI;

public class ValueEvent implements ValueEventAPI {
	
	private final ValueControllerAPI source;
	
	private final int formelValue;
	
	private final int newValue;
	
	private final int formerTempValue;
	
	private final int newTempValue;

	public ValueEvent(ValueControllerAPI value, int formelValue, int newValue,
			int formerTempValue, int newTempValue) {
		super();
		this.source = value;
		this.formelValue = formelValue;
		this.newValue = newValue;
		this.formerTempValue = formerTempValue;
		this.newTempValue = newTempValue;
	}

	@Override
	public ValueControllerAPI getSource() {
		return source;
	}

	@Override
	public int getFormerValue() {
		return formelValue;
	}

	@Override
	public int getNewValue() {
		return newValue;
	}

	@Override
	public int getFormerTempValue() {
		return formerTempValue;
	}

	@Override
	public int getNewTempValue() {
		return newTempValue;
	}
	
	
	

}
