package vampire.editor.plugin.api.domain.sheet;

public interface Value extends ValueAPI{

	public int getValue();

	/**
	 * Sets the value.
	 * @param value
	 */
	public void setValue(int value);

	public int getMaxValue();

	public int getMinValue();

	public Value clone();

	public int getTempValue();

	public void setTempValue(int tempValue);

	public int hashCode();

	/**
	 * Returns true if and only if value, temporary value, minimum value and maximal value are pairwise equal.<br>
	 */
	public boolean equals(Object obj);

}