package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritsListener;
import vampire.editor.plugin.api.domain.sheet.MeritsAPI;
import vampire.editor.plugin.api.view.sheet.MeritView;

public interface MeritsControllerAPI extends Iterable<MeritEntryControllerAPI>{
	
	public MeritsAPI getMerits();
	
	public MeritView getView();
	
	public void addMerit(MeritEntryControllerAPI merit);
	
	public void removeMerit(MeritEntryControllerAPI merit);
	
	public void addListener(MeritsListener l);
	
	public void removeListener(MeritsListener l);
	
	public int size();
	
	public int indexOf(MeritEntryControllerAPI controller);
	
	public MeritEntryControllerAPI getController(int index);
	
	
}
