package vampire.editor.plugin.api.view;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.view.application.SheetViewFactory;

public interface GUIPlugin {
	
	public void addItemToMenuBar(Trigger trigger, String... menus);
	
	public String openFileView();
	
	public void createErrorMessage(String message);
	
	public void setVisible();
	
	public SheetViewFactory getFactory();
	
	public void sheetLoaded(SheetControllerAPI controller);
	
	
	
	

}
