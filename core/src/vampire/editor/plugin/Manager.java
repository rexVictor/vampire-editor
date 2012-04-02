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
	
	private class PluginActivator implements Runnable{
		
		private final Activator activator;
		
		

		public PluginActivator(Activator activator) {
			super();
			this.activator = activator;
		}



		@Override
		public void run() {
			activator.setManager(Manager.this);
			
		}
		
	}
	
	public void registerPlugin(Activator activator){
		Runnable runner = new PluginActivator(activator);
		Thread thread = new Thread(runner);
		thread.start();
	}

}
