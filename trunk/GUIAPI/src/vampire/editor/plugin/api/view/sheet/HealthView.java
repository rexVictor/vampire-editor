package vampire.editor.plugin.api.view.sheet;

import java.util.List;

import vampire.editor.plugin.api.view.events.HealthViewListener;

public interface HealthView {
	
	public List<HealthEntryView> getEntries();
	
	public void addHealthEntryView(HealthEntryView healthEntryView);
	
	public void removeHealthEntryView(HealthEntryView healthEntryView);
	
	public void addListener(HealthViewListener listener);

}
