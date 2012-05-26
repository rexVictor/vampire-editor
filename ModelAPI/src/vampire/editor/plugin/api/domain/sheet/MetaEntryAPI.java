package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributesAPI;

public interface MetaEntryAPI extends Nameable{

	@Override
	public String getName();

	public String getValue();

	@Override
	public MetaEntryAPI clone();
	
	public MetaEntryViewAttributesAPI getViewAtts();

}