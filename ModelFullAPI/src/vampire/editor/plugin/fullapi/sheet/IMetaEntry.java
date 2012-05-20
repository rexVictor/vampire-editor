package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;

public interface IMetaEntry extends MetaEntryAPI{

	@Override
	public String getName();

	public void setName(String name);

	@Override
	public String getValue();

	public void setValue(String value);

	@Override
	public MetaEntryAPI clone();

}