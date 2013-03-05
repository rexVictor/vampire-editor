package vampire.editor.plugin.api.view.sheet;

import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttibutesAPI;
import vampire.editor.plugin.api.view.events.MeritEntryViewListener;

public interface MeritEntryView {
	
	public void setCost(int cost);
	
	public void setText(String text);
	
	public void addListener(MeritEntryViewListener listener);
	
	public MeritEntryView clone();

	public MeritEntryViewAttibutesAPI getViewAttributes();

}
