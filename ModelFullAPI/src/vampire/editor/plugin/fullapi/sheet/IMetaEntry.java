package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;
import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributesAPI;

public interface IMetaEntry extends MetaEntryAPI{

	@Override
	public String getName();

	public void setName(String name);

	@Override
	public String getValue();

	public void setValue(String value);
	
	public void setViewAtts(MetaEntryViewAttributesAPI viewAtts);
	
	@Override
	public MetaEntryViewAttributesAPI getViewAtts();

	@Override
	public MetaEntryAPI clone();

}