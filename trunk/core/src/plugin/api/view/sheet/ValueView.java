package plugin.api.view.sheet;

import plugin.api.view.events.ValueViewListener;

public interface ValueView {
	
	public void setValue(int value);
	
	public void setTempValue(int value);
	
	public void addListener(ValueViewListener listener);
	
	

}