package vampire.editor.plugin.api.plugin;

import java.nio.file.Path;

import vampire.editor.plugin.api.application.sheet.controller.SheetControllerAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;

public interface ManagerAPI {
	
	public GUIPlugin getGUI();
	
	public ResourcesHolderAPI getResourcesHolder();
	
	public void setGUIPlugin(GUIPlugin gui);
	
	public GeneralControllerAPI getGeneralController();
	
	public void closed(SheetControllerAPI controller);
	
	public void selectedSheetChanged(SheetControllerAPI controller);
	
	public void setDefaultImporter(SheetImporter importer);
	
	public void addDocumentListener(DocumentListener listener);
	
	public void removeDocumentListener(DocumentListener listener);
	
	public void addImporter(SheetImporter importer);
	
	public void open(Path path);
	

}
