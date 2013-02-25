package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritsListener;
import vampire.editor.plugin.api.domain.sheet.MeritsAPI;
import vampire.editor.plugin.api.view.sheet.MeritView;

public interface MeritsControllerAPI {
	
	public MeritsAPI getMerits();
	
	public MeritView getView();
	
	public void addMerit(MeritEntryControllerAPI merit);
	
	public void removeMerit(MeritEntryControllerAPI merit);
	
	public void addListener(MeritsListener l);
	
	public void removeListener(MeritsListener l);
	
}
