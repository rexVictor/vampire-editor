package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;

public interface GUIPlugin {

	public String openFileView();
	
	public void addItemToMenuBar(Trigger trigger, String... items);
	
	public void setVisible();
	
	public SheetViewFactory getFactory();
	
	public void sheetLoaded(SheetControllerAPI controller);
	
	public void addImportFileExtension(String extension);
}
