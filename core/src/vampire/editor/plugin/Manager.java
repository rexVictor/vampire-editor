package vampire.editor.plugin;

import java.util.HashMap;
import java.util.Map;

public class Manager {
	
	private final Map<String, Facade> facades = new HashMap<>();
	
	public void offer(Facade facade){
		facades.put(facade.getClass().getName(), facade);
	}
	
	public Facade demand(String name){
		return facades.get(name);
	}
	
	
	
	public void registerPlugin(Activator activator){
		
	}

}
