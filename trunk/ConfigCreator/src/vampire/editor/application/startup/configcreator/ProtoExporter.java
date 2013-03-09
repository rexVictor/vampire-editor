package vampire.editor.application.startup.configcreator;

public class ProtoExporter {
	
private final String name;
	
	private final String jarName;
	
	private final String ending;

	public ProtoExporter(String name, String jarName, String ending) {
		super();
		this.name = name;
		this.jarName = jarName;
		this.ending = ending;
	}

	public String getName() {
		return name;
	}

	public String getJarName() {
		return jarName;
	}

	public String getFormat() {
		return ending;
	}

}
