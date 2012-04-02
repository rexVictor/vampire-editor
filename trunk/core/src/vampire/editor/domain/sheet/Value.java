package vampire.editor.domain.sheet;

import java.io.Serializable;

import vampire.editor.plugin.api.domain.sheet.ValueAPI;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

public class Value implements ValueAPI, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2516048495698715631L;
	
	
	public static final int MIN_VALUE = 0;
	public static final int MAX_VALUE = 10;
	
	
	private int value;
	
	private int tempValue;
	
	private final int maxValue;
	private final int minValue;
	
	private final ValueViewAttributes viewAttributes;
	
	public Value(ValueViewAttributes viewAttributes){
		this(0,viewAttributes);
	}
	
	public Value(int value, ValueViewAttributes viewAttributes){
		this(value,MAX_VALUE, viewAttributes);
	}
	
	public Value(int value, int maxValue, ValueViewAttributes viewAttributes){
		this(value, MIN_VALUE, maxValue, viewAttributes);
	}
	
	public Value(int value, int minValue, int maxValue, ValueViewAttributes viewAttributes){
		this.minValue = minValue;
		this.maxValue = maxValue;
		setValue(value);
		this.viewAttributes = viewAttributes;
	}

	@Override
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		if (minValue<=value&&value<=maxValue)
			this.value = value;
		else
			throw new IllegalValueException();
	}

	@Override
	public int getMaxValue() {
		return maxValue;
	}

	@Override
	public int getMinValue() {
		return minValue;
	}

	@Override
	public ValueViewAttributes getViewAttributes() {
		return viewAttributes;
	}
	
	
	
	@Override
	public String toString(){
		return value+"";
	}
	
	@Override
	public Value clone(){
		return new Value(value, minValue, maxValue, viewAttributes);
	}

	public int getTempValue() {
		return tempValue;
	}

	public void setTempValue(int tempValue) {
		this.tempValue = tempValue;
	}
	

	
	
	
	
	
	

}
