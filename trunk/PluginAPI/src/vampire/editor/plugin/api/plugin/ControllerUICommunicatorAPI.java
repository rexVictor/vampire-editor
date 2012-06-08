package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;

public interface ControllerUICommunicatorAPI {
	
	public void close(SheetControllerAPI controller);
	
	public void closed(SheetControllerAPI controller);
	
	public void add(SheetControllerAPI controller);
	
	
	

}
