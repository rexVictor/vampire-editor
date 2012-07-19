package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.PublicCloneable;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;

public interface ValueAPI extends PublicCloneable{

	public int getValue();

	public int getMaxValue();

	public int getMinValue();
	
	public int getTempValue();
	
	public ValueViewAttributesAPI getViewAtts();
	
	@Override
	public ValueAPI clone();

}