package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;

public interface GeneralControllerAPI {
	
	public void open(VampireDocumentAPI sheet);
	
	public SheetControllerAPI getCurrentController();
	
}
