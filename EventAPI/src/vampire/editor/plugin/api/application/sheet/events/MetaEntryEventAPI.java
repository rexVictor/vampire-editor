package vampire.editor.plugin.api.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.MetaEntryControllerAPI;

public interface MetaEntryEventAPI {
	
	public MetaEntryControllerAPI getSource();
	
	public String getFormerValue();
	
	public String getNewValue();
	
	public String getFormerName();
	
	public String getNewName();
	

}
