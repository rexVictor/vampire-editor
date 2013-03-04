package vampire.editor.plugin.api.view.sheet;

import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributesAPI;
import vampire.editor.plugin.api.view.events.TraitViewListener;

public interface TraitView {
	
	public void setName(String name);
	
	public void addListener(TraitViewListener listener);
	
	public ValueView getValueView();
	
	public TraitView clone();
	
	public TraitViewAttributesAPI getViewAttributes();
	
}
