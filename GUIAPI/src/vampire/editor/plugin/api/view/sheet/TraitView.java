package vampire.editor.plugin.api.view.sheet;

import vampire.editor.plugin.api.view.events.TraitViewListener;

public interface TraitView {
	
	public void setName(String name);
	
	public void addListener(TraitViewListener listener);

}
