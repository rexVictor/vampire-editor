package vampire.editor.plugin.api.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;

public interface MetaEntry extends MetaEntryAPI{

	public String getName();

	public void setName(String name);

	public String getValue();

	public void setValue(String value);

	public MetaEntry clone();

}