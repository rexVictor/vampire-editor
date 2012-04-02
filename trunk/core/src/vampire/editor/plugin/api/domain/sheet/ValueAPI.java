package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

public interface ValueAPI {

	public int getValue();

	public int getMaxValue();

	public int getMinValue();
	
	public int getTempValue();
	
	public ValueViewAttributes getViewAttributes();	

}