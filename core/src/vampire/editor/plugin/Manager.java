package vampire.editor.plugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import vampire.editor.application.GeneralController;

import vampire.editor.domain.Config;

import vampire.editor.plugin.api.plugin.Activator;
import vampire.editor.plugin.api.plugin.Facade;
import vampire.editor.plugin.api.plugin.GeneralControllerAPI;
import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.api.plugin.ResourcesHolderAPI;
import vampire.editor.plugin.api.view.GUIPlugin;

public class Manager implements ManagerAPI{
	
	private final Config config;
	
	private final Map<String, Facade> facades = new HashMap<>();
	
	private final GeneralController controller;
	
	private final List<Plugin> plugins = new LinkedList<>();
	
	private /*final*/ GUIPlugin gui;
	
	public Manager(Config config, GeneralController controller){
		this.config = config;
		this.controller = controller;
		initialize();
		gui.setVisible();
	}
	
	private void initialize(){
		try {
			Activator gui = config.getGUI().newInstance();
			gui.setManager(this);			
			Activator loader = config.getLoader().newInstance();
			loader.setManager(this);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);			
		}
		Map<String, Plugin> plugins = config.getPlugins();
		List<String> keys = new ArrayList<>(plugins.keySet());
		while (!keys.isEmpty()) {
			for (int i = 0; i < keys.size(); i++){
				String key = keys.get(i);
				Plugin plugin = plugins.get(key);
				List<Plugin> dependencies = plugin.getDependencies();
				if (dependencies.isEmpty() || this.plugins.containsAll(dependencies)) {
					try {
						Activator activator = plugin.getActivator().newInstance();
						activator.setManager(this);
						plugins.put(key, plugin);
						keys.remove(i);
						i--;
					} catch (InstantiationException | IllegalAccessException e) {
						throw new RuntimeException(e);
					}
				}
			}
		}
		
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
	
	
	
	public GeneralController getController(){
		return controller;
	}

	@Override
	public GUIPlugin getGUI() {
		return gui;
	}

	
	
	
	
	

	@Override
	public ResourcesHolderAPI getResourcesHolder() {
		return config.getResourcesHolder();
	}

	@Override
	public void setGUIPlugin(GUIPlugin gui) {
		if (this.gui == null)
			this.gui = gui;
		
	}

	@Override
	public GeneralControllerAPI getGeneralController() {
		return controller;		
	}
	
	

}
