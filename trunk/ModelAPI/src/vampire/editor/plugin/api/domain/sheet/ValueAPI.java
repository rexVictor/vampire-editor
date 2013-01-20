package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.PublicCloneable;

public interface ValueAPI extends PublicCloneable{

	public int getValue();

	public int getMaxValue();

	public int getMinValue();
	
	public int getTempValue();
	
	@Override
	public ValueAPI clone();

}