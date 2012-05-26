package vampire.editor.domain.sheet;

import java.io.Serializable;

import vampire.editor.plugin.fullapi.sheet.IValue;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;


/**
 * Wrapper object for the value of a Trait. <br>
 * It has a minimal and a maximal Value. A new value must be
 * between the both (inclusive).
 * @author rex
 *
 */
public class Value implements Serializable, IValue {
	
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
	
	private IValueViewAttributes viewAtts;
	
	public Value(){
		this(null);
	}
	
	/**
	 * Constructs a new Value with the specified viewAttributes, 
	 * 0 as value, MAX_VALUE as maximum and MIN_VALUE as minimum.
	 * @param viewAttributes
	 */
	public Value(IValueViewAttributes viewAttributes){
		this(0,viewAttributes);
	}
	
	/**
	 * Constructs a new Value with the specified viewAttributes,
	 * specified value, MAX_VALUE as maximum and MIN_VALUE as minimum.
	 * @param value
	 * @param viewAttributes
	 */
	public Value(int value, IValueViewAttributes viewAttributes){
		this(value,MAX_VALUE, viewAttributes);
	}
	
	/**
	 * Constructs a new Value with the specified viewAttributes,
	 * specified value, specified maximum and MIN_VALUE as minimum.
	 * @param value
	 * @param maxValue
	 * @param viewAttributes
	 */
	public Value(int value, int maxValue, IValueViewAttributes viewAttributes){
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
	public Value(int value, int minValue, int maxValue, IValueViewAttributes viewAttributes){
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
	public IValueViewAttributes getViewAtts() {
		return viewAtts;
	}
	
	
	
	@Override
	public String toString(){
		return "{"+minValue+"<="+value+"~"+tempValue+"<="+maxValue+"}";
		
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
		return minValue+2*maxValue+4*value+8*tempValue;		
	}
	
	
	@Override
	public final boolean equals(Object obj){
		if (obj instanceof Value){
			Value comp = (Value) obj;
			return comp.value == value && comp.tempValue == tempValue && comp.maxValue == maxValue && comp.minValue == minValue;
		}
		return false;
	}
	
	@Override
	public void setViewAtts(IValueViewAttributes atts){
		if (viewAtts == null)
			this.viewAtts = atts;
	}
}
