package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.ValueAPI;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;



public interface IValue extends ValueAPI{

	public static final int MIN_VALUE = 0;
	public static final int MAX_VALUE = 10;

	/**
	 * Returns the Value
	 * @return value
	 */
	@Override
	public  int getValue();

	/**
	 * Sets the value specified if and only if it is inclusively
	 * between the minimum and maximum used during construction.
	 * If it is not an {@link IllegalValueException} is thrown.
	 * @param value
	 * @throws IllegalValueException
	 */
	public  void setValue(int value);

	/**
	 * Returns the maximum the value can be set to.
	 */
	@Override
	public  int getMaxValue();

	/**
	 * Returns the minimum the value can be set to.
	 */
	@Override
	public  int getMinValue();

	/**
	 * Returns the viewAttributes
	 */
	@Override
	public  IValueViewAttributes getViewAtts();

	/**
	 * Returns a copy of this Value. <br>
	 * The clone and this share the same reference to the view attributes.
	 */
	public  IValue clone();

	/**
	 * Returns the temporary value.
	 */
	@Override
	public  int getTempValue();

	/**
	 * Sets the temporary value.
	 * @param tempValue
	 */
	public  void setTempValue(int tempValue);

	/**
	 * Returns true if and only if the object is identical 
	 * via reference.
	 * Implementation Note: This method is overridden as final!
	 */
	@Override
	public boolean equals(Object obj);

}