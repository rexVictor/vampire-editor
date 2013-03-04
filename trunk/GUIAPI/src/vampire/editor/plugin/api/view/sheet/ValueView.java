package vampire.editor.plugin.api.view.sheet;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;
import vampire.editor.plugin.api.view.events.ValueViewListener;

public interface ValueView {
	
	public void setValue(int value);
	
	public void setTempValue(int value);
	
	public void addListener(ValueViewListener listener);
	
	public void addCircle();
	
	public void removeCircle();
	
	public ValueView clone();
	
	public ValueViewAttributesAPI getViewAttributes();
	
	

}
