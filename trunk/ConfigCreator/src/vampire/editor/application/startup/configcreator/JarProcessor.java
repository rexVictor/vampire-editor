package vampire.editor.application.startup.configcreator;

import org.jdom2.Attribute;
import org.jdom2.Element;

import vampire.editor.plugin.api.plugin.Activator;

class JarProcessor implements ElementProcessor{
	
	private final NullProcessor nullProcessor = new NullProcessor();
	private final PathClassProcessor pathClassProcessor = new PathClassProcessor();

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String name = element.getAttributeValue("name");
		Attribute path = element.getAttribute("path");
		Attribute file = element.getAttribute("file");
		ClassLoader loader = null;
		if(path == null){
			loader = nullProcessor.process(null, null, null, null);
		}
		else {
			loader = pathClassProcessor.process(null, null, path, file);
		}
		String clazzname = element.getAttributeValue("class");
		try {
			@SuppressWarnings("unchecked")
			Class<Activator> clazz = (Class<Activator>) loader.loadClass(clazzname);
			configCreator.put(name, clazz);
		} catch (ClassNotFoundException e) {
			throw new ConfigImportException(e);
		}
	}

	@Override
	public String getName() {
		return "jar";
	}

}
