package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;

public interface IMetaEntry extends MetaEntryAPI{

	public String getName();

	public void setName(String name);

	public String getValue();

	public void setValue(String value);

	public MetaEntryAPI clone();

}