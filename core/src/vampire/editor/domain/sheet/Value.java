package vampire.editor.domain.sheet;

import java.io.Serializable;

import vampire.editor.plugin.api.domain.sheet.ValueAPI;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.view.sheet.ValueView;

/**
 * Wrapper object for the value of a Trait. <br>
 * It has a minimal and a maximal Value. A new value must be
 * between the both (inclusive).
 * @author rex
 *
 */
public class Value implements ValueAPI, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2516048495698715631L;
	
	public static final int MIN_VALUE = 0;
	public static final int MAX_VALUE = 10;
	
	/**
	 * The value this object wraps.
	 */
	private int value;
	
	/**
	 * The temporary value.
	 */
	private int tempValue;
	
	/**
	 * The maximum value can be set to. 
	 */
	private final int maxValue;
	
	/**
	 * The minimum value can be set to.
	 */
	
	private final int minValue;
	
	/**
	 * The Attributes for the view how to present this.
	 * {@link ValueView} 
	 */
	// TODO Hier noch genau rausarbeiten wie das gemacht werden soll.
	private final ValueViewAttributes viewAttributes;
	
	/**
	 * Constructs a new Value with the specified viewAttributes, 
	 * 0 as value, MAX_VALUE as maximum and MIN_VALUE as minimum.
	 * @param viewAttributes
	 */
	public Value(ValueViewAttributes viewAttributes){
		this(0,viewAttributes);
	}
	
	/**
	 * Constructs a new Value with the specified viewAttributes,
	 * specified value, MAX_VALUE as maximum and MIN_VALUE as minimum.
	 * @param value
	 * @param viewAttributes
	 */
	public Value(int value, ValueViewAttributes viewAttributes){
		this(value,MAX_VALUE, viewAttributes);
	}
	
	/**
	 * Constructs a new Value with the specified viewAttributes,
	 * specified value, specified maximum and MIN_VALUE as minimum.
	 * @param value
	 * @param maxValue
	 * @param viewAttributes
	 */
	public Value(int value, int maxValue, ValueViewAttributes viewAttributes){
		this(value, MIN_VALUE, maxValue, viewAttributes);
	}
	
	/**
	 * Constructs a new Value with the specified viewAttributes,
	 * specified value, specified maximum and specified minimum.
	 * @param value
	 * @param minValue
	 * @param maxValue
	 * @param viewAttributes
	 */
	public Value(int value, int minValue, int maxValue, ValueViewAttributes viewAttributes){
		this.minValue = minValue;
		this.maxValue = maxValue;
		setValue(value);
		this.viewAttributes = viewAttributes;
	}
	
	/**
	 * Returns the Value
	 * @return value
	 */
	@Override
	public int getValue() {
		return value;
	}
	
	/**
	 * Sets the value specified if and only if it is inclusively
	 * between the minimum and maximum used during construction.
	 * If it is not an {@link IllegalValueException} is thrown.
	 * @param value
	 * @throws IllegalValueException
	 */
	public void setValue(int value) {
		if (minValue<=value&&value<=maxValue)
			this.value = value;
		else
			throw new IllegalValueException();
	}
	
	/**
	 * Returns the maximum the value can be set to.
	 */
	@Override
	public int getMaxValue() {
		return maxValue;
	}
	
	/**
	 * Returns the minimum the value can be set to.
	 */
	@Override
	public int getMinValue() {
		return minValue;
	}
	
	/**
	 * Returns the viewAttributes
	 */
	@Override
	public ValueViewAttributes getViewAttributes() {
		return viewAttributes;
	}
	
	
	/**
	 * Returns the value as a String.
	 */
	@Override
	public String toString(){
		return value+"";
	}
	
	/**
	 * Returns a copy of this Value. <br>
	 * The clone and this share the same reference to the view attributes.
	 */
	@Override
	public Value clone(){
		return new Value(value, minValue, maxValue, viewAttributes);
	}
	
	/**
	 * Returns the temporary value.
	 */
	public int getTempValue() {
		return tempValue;
	}
	
	/**
	 * Sets the temporary value.
	 * @param tempValue
	 */
	public void setTempValue(int tempValue) {
		this.tempValue = tempValue;
	}
	
	/**
	 * Returns true if and only if the object is identical 
	 * via reference.
	 * Implementation Note: This method is overridden as final!
	 */
	@Override
	public final boolean equals(Object obj){
		return this == obj;
	}
}
