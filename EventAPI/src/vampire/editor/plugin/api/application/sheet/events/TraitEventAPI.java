package vampire.editor.plugin.api.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;

public interface TraitEventAPI {
	
	public TraitControllerAPI getSource();
	
	public String getFormerName();
	
	public String getNewName();
	
	

}
