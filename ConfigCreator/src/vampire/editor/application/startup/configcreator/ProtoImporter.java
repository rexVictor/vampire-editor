package vampire.editor.application.startup.configcreator;

public class ProtoImporter {
	
	private final String name;
	
	private final String jarName;
	
	private final String ending;

	public ProtoImporter(String name, String jarName, String ending) {
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
