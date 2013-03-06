package vampire.editor.domain.config;

import vampire.editor.plugin.api.plugin.Activator;

public class Importer {
	
	private final Class<Activator> activator;
	
	private final String name;
	
	private final String format;

	public Importer(Class<Activator> activator, String name, String format) {
		super();
		this.activator = activator;
		this.name = name;
		this.format = format;
	}

	public Class<Activator> getActivator() {
		return activator;
	}

	public String getName() {
		return name;
	}

	public String getFormat() {
		return format;
	}
	
	

	
}