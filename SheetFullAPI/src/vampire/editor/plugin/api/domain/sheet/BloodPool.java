package vampire.editor.plugin.api.domain.sheet;

public interface BloodPool extends BloodPoolAPI{

	public abstract int getValue();

	public abstract void setValue(int value);

	public abstract int getMaxValue();

	public abstract void setMaxValue(int maxValue);

}