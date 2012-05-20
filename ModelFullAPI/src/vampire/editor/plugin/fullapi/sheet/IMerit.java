package vampire.editor.plugin.fullapi.sheet;

import vampire.editor.plugin.api.domain.sheet.MeritAPI;

public interface IMerit extends MeritAPI {

	public void setCost(int cost);

	public void setName(String name);
	
	@Override
	public IMerit clone();

}