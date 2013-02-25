package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.MeritsControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritsEventAPI;

public class MeritsEvent implements MeritsEventAPI{
	
	private final MeritsControllerAPI source;
	
	private final MeritEntryControllerAPI added;
	
	private final MeritEntryControllerAPI removed;

	public MeritsEvent(MeritsControllerAPI source,
			MeritEntryControllerAPI added, MeritEntryControllerAPI removed) {
		super();
		this.source = source;
		this.added = added;
		this.removed = removed;
	}

	@Override
	public MeritsControllerAPI getSource() {
		return source;
	}

	@Override
	public MeritEntryControllerAPI getAdded() {
		return added;
	}

	@Override
	public MeritEntryControllerAPI getRemoved() {
		return removed;
	}
	
	

}
