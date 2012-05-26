package vampire.editor.gui.swing.view;

import vampire.editor.plugin.api.view.events.MetaEntryViewEvent;

public class SMetaEntryViewEvent implements MetaEntryViewEvent{
	
	private final String title;
	
	private final String content;
	
	

	public SMetaEntryViewEvent(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getContent() {
		return content;
	}

}
