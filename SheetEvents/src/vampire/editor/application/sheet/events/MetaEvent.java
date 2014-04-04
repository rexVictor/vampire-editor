package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.MetaControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MetaEventAPI;

public class MetaEvent extends AbstractNonLeafEvent<MetaControllerAPI, MetaEntryControllerAPI> implements MetaEventAPI{

	public MetaEvent(MetaControllerAPI source, MetaEntryControllerAPI reason,
			int index) {
		super(source, reason, index);
	}

}
