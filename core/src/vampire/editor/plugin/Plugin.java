package vampire.editor.plugin;

import java.util.Collections;
import java.util.List;

import vampire.editor.plugin.api.plugin.Activator;

public class Plugin {
	
	private final List<Plugin> dependencies;
	
	private final Class<Activator> activator;
	
	private final String key;

	public Plugin(List<Plugin> dependencies, Class<Activator> activator,
			String key) {
		super();
		this.dependencies = Collections.unmodifiableList(dependencies);
		this.activator = activator;
		this.key = key;
	}

	public List<Plugin> getDependencies() {
		return dependencies;
	}

	public Class<Activator> getActivator() {
		return activator;
	}

	public String getKey() {
		return key;
	}
	
	
	
	
	
	
	
	

}
