package vampire.editor.plugin.api.view.sheet;

import vampire.editor.plugin.api.domain.PopupEntriesAPI;
import vampire.editor.plugin.api.view.events.MetaEntryViewListener;

public interface MetaEntryView {
	
	public void setTitle(String title);
	
	public void setContent(String content);
	
	public void setPopup(PopupEntriesAPI entries);
	
	public void add(MetaEntryViewListener listener);
	
	public void remove(MetaEntryViewListener listener);

}
