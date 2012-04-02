package application.sheet.events;

import application.sheet.controller.ValueController;
import plugin.api.application.sheet.events.ValueEventAPI;

public class ValueEvent implements ValueEventAPI {
	
	private final ValueController source;
	
	private final int formelValue;
	
	private final int newValue;
	
	private final int formerTempValue;
	
	private final int newTempValue;

	public ValueEvent(ValueController value, int formelValue, int newValue,
			int formerTempValue, int newTempValue) {
		super();
		this.source = value;
		this.formelValue = formelValue;
		this.newValue = newValue;
		this.formerTempValue = formerTempValue;
		this.newTempValue = newTempValue;
	}

	@Override
	public ValueController getSource() {
		return source;
	}

	@Override
	public int getFormelValue() {
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
