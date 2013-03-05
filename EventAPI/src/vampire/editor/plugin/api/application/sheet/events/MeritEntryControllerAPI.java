package vampire.editor.plugin.api.application.sheet.events;

import vampire.editor.plugin.api.domain.sheet.MeritAPI;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;

public interface MeritEntryControllerAPI {
	
	public MeritAPI getMerit();
	
	public MeritEntryView getView();
	
	public void setCost(int cost);
	
	public void setName(String name);
	
	public void addListener(MeritEntryListener l);
	
	public void removeListener(MeritEntryListener l);
	
	public MeritEntryControllerAPI clone();
	

}
