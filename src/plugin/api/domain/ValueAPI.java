package plugin.api.domain;

import plugin.api.domain.sheet.view.ValueViewAttributes;

public interface ValueAPI {

	public int getValue();

	public int getMaxValue();

	public int getMinValue();
	
	public int getTempValue();
	
	public ValueViewAttributes getViewAttributes();	

}