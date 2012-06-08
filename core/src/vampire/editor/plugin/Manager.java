package vampire.editor.plugin;

import java.util.HashMap;
import java.util.Map;

import vampire.editor.application.GeneralController;
import vampire.editor.domain.Dictionary;
import vampire.editor.plugin.api.application.sheet.events.SheetEventAPI;
import vampire.editor.plugin.api.application.sheet.events.SheetListener;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;
import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.Facade;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.plugin.ResourcesHolderAPI;
import vampire.editor.plugin.api.view.GUIPlugin;

public class Manager implements SheetListener, ManagerAPI{
	
	private final Map<String, Facade> facades = new HashMap<>();
	
	private final Map<String, Dictionary> dictionaries = new HashMap<>();
	
	private final ResourcesHolder resourcesHolder = new ResourcesHolder();
	

	
	private final GUIPlugin guiplugin = null;
	
	private final SheetLoaders sheetLoaders = new SheetLoaders();
	
	private GeneralController controller;
	
	public Manager(){
		
	}
	
	public void offer(Facade facade){
		facades.put(facade.getClass().getName(), facade);		
	}
	
	public Facade demand(String name){
		return facades.get(name);
	}
	
	
	
	public void registerPlugin(Activator activator){
		activator.setManager(this);
	}
	
	public void registerSheetLoader(SheetLoader loader){
		
	}
	
	public void addDictionary(Dictionary dictionary){
		
	}
	
	public void setGeneralController(GeneralController controller){
		this.controller = controller;
	}
	
	public GeneralController getController(){
		return controller;
	}

	@Override
	public void sheetConstructed(SheetEventAPI event) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public GUIPlugin getGUI() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SheetAPI getDefaultSheet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

	@Override
	public ResourcesHolderAPI getResourcesHolder() {
		// TODO Auto-generated method stub
		return resourcesHolder;
	}
	
	

}
