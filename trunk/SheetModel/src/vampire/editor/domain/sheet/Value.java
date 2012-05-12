package vampire.editor.domain.sheet;

import java.io.Serializable;

import vampire.editor.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.domain.sheet.ValueAPI;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;
import vampire.editor.plugin.fullapi.sheet.IValue;


/**
 * Wrapper object for the value of a Trait. <br>
 * It has a minimal and a maximal Value. A new value must be
 * between the both (inclusive).
 * @author rex
 *
 */
public class Value implements ValueAPI, Serializable, IValue {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2516048495698715631L;
	
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
	
	private ValueViewAttributes viewAtts;
	
	public Value(){
		this(null);
	}
	
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
		this.viewAtts = viewAttributes;
	}
	
	@Override
	public int getValue() {
		return value;
	}
	
	@Override
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
	public ValueViewAttributesAPI getViewAtts() {
		return viewAtts;
	}
	
	
	/**
	 * Returns the value as a String.
	 */
	@Override
	public String toString(){
		return value+"";
	}
	
	
	@Override
	public Value clone(){
		return new Value(value, minValue, maxValue, viewAtts);
	}
	
	
	@Override
	public int getTempValue() {
		return tempValue;
	}
	
	
	@Override
	public void setTempValue(int tempValue) {
		this.tempValue = tempValue;
	}
	
	@Override
	public final int hashCode(){
		return super.hashCode();
	}
	
	
	@Override
	public final boolean equals(Object obj){
		return this == obj;
	}
	
	public void setViewAtts(ValueViewAttributes atts){
		this.viewAtts = atts;
	}
}
