package vampire.editor.plugin.api.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;

public interface TraitMouseEventAPI {
	
	public int getButton();
	
	public int getClickCount();
	
	public TraitControllerAPI getSource();

}
