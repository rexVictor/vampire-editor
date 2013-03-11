/*******************************************************************************
 * Vampire Editor Model Implementation.
 * Copyright (C) 2013  Matthias Johannes Reimchen
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.domain.sheet;

import java.io.Serializable;

import vampire.editor.plugin.api.domain.sheet.ValueAPI;


/**
 * Wrapper object for the value of a Trait. <br>
 * It has a minimal and a maximal Value. A new value must be
 * between the both (inclusive).
 * @author rex
 *
 */
public class Value implements Serializable, ValueAPI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2516048495698715631L;
	
	private static final int MAX_VALUE = 10;
	
	private static final int MIN_VALUE = 0;
	
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
	
	public Value(){
		this(0);
	}
	
	/**
	 * Constructs a new Value with the specified viewAttributes,
	 * specified value, MAX_VALUE as maximum and MIN_VALUE as minimum.
	 * @param value
	 * @param viewAttributes
	 */
	private Value(int value){
		this(value,MAX_VALUE);
	}
	
	/**
	 * Constructs a new Value with the specified viewAttributes,
	 * specified value, specified maximum and MIN_VALUE as minimum.
	 * @param value
	 * @param maxValue
	 * @param viewAttributes
	 */
	private Value(int value, int maxValue){
		this(value, MIN_VALUE, maxValue);
	}
	
	/**
	 * Constructs a new Value with the specified viewAttributes,
	 * specified value, specified maximum and specified minimum.
	 * @param value
	 * @param minValue
	 * @param maxValue
	 * @param viewAttributes
	 */
	public Value(int value, int minValue, int maxValue){
		this.minValue = minValue;
		this.maxValue = maxValue;
		setValue(value);
	}
	
	@Override
	public int getValue() {
		return value;
	}
	/**
	 * Sets the value.
	 * @param value
	 * @throws IllegalValueException, if !(minValue<= value <= maxValue)
	 */
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
	public String toString(){
		return "{"+minValue+"<="+value+"~"+tempValue+"<="+maxValue+"}";
		
	}
	
	@Override
	public Value clone(){
		Value clone = new  Value(value, minValue, maxValue);
		clone.setTempValue(tempValue);
		return clone;
	}
	
	
	@Override
	public int getTempValue() {
		return tempValue;
	}
	
	
	public void setTempValue(int tempValue) {
		this.tempValue = tempValue;
	}
	
	@Override
	public final int hashCode(){
		return minValue+2*maxValue+4*value+8*tempValue;		
	}
	
	/**
	 * Returns true if and only if value, temporary value, minimum value and maximal value are pairwise equal.<br>
	 */
	@Override
	public boolean equals(Object obj){
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj instanceof Value){
			Value comp = (Value) obj;
			return comp.value == value && comp.tempValue == tempValue 
					&& comp.maxValue == maxValue && comp.minValue == minValue;
		}
		return false;
	}
}
