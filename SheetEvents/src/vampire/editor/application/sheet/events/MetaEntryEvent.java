package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaEntryEventAPI;

public class MetaEntryEvent implements MetaEntryEventAPI{
	
	private final String formerValue;
	
	private final String newValue;
	
	private final String formerKey;
	
	private final String newKey;
	
	private final MetaEntryControllerAPI source;
	

	public MetaEntryEvent(String formerValue, String newValue,
			String formerKey, String newKey, MetaEntryControllerAPI source) {
		super();
		this.formerValue = formerValue;
		this.newValue = newValue;
		this.formerKey = formerKey;
		this.newKey = newKey;
		this.source = source;
	}

	@Override
	public String getFormerValue() {
		return formerValue;
	}

	@Override
	public String getNewValue() {
		return newValue;
	}

	@Override
	public MetaEntryControllerAPI getSource() {
		return source;
	}

	@Override
	public String getFormerName() {
		return formerKey;
	}

	@Override
	public String getNewName() {
		return newKey;
	}

}
