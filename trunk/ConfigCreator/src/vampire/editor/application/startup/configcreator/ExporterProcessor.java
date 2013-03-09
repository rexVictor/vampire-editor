package vampire.editor.application.startup.configcreator;

import org.jdom2.Element;

class ExporterProcessor implements ElementProcessor{
	
	private final JarProcessor processor = new JarProcessor();

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String name = element.getAttributeValue(ConfigStrings.NAME);
		String format = element.getAttributeValue(ConfigStrings.FORMAT);
		Element jar = element.getChild(ConfigStrings.JAR);
		jar.setAttribute(ConfigStrings.NAME, name);
		processor.process(jar, configCreator);
		ProtoExporter exporter = new ProtoExporter(name, name, format);
		configCreator.put(name, exporter);
	}

	@Override
	public String getName() {
		return ConfigStrings.EXPORTER;
	}

}
