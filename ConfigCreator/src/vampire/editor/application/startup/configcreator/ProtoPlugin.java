package vampire.editor.application.startup.configcreator;

import java.util.List;

public class ProtoPlugin {
	
	private final List<String> dependencies;
	
	private final String activator;
	
	private final String name;


	public ProtoPlugin(List<String> dependencies, String activator, String name) {
		super();
		this.dependencies = dependencies;
		this.activator = activator;
		this.name = name;
	}

	public List<String> getDependencies() {
		return dependencies;
	}

	public String getActivator() {
		return activator;
	}

	public String getName() {
		return name;
	}
	
}
