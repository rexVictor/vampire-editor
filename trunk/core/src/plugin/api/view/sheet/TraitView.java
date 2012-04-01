package plugin.api.view.sheet;

import plugin.api.view.events.TraitViewListener;

public interface TraitView {
	
	public void setName(String name);
	
	public void addListener(TraitViewListener listener);

}
