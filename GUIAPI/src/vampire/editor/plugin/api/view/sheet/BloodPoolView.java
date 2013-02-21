package vampire.editor.plugin.api.view.sheet;

import vampire.editor.plugin.api.view.events.BloodPoolViewListener;

public interface BloodPoolView {
	
	public void addListener(BloodPoolViewListener listener);
	
	public void setValue(int value);
	
	public void setMaxValue(int maxValue);

}
