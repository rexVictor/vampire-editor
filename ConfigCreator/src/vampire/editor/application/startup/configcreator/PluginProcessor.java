package vampire.editor.application.startup.configcreator;

import java.util.LinkedList;
import java.util.List;

import org.jdom2.Element;

public class PluginProcessor implements ElementProcessor{
	
	private final JarProcessor processor = new JarProcessor();

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String key = element.getAttributeValue(ConfigStrings.NAME);
		List<String> dependenciesStringList = new LinkedList<>();
		Element dependenciesElement = element.getChild(ConfigStrings.DEPENDENCIES);
		if (dependenciesElement != null){
			List<Element> dependencies = dependenciesElement.getChildren(ConfigStrings.DEPENDS);
			for (Element e : dependencies){
				dependenciesStringList.add(e.getAttributeValue(ConfigStrings.NAME));
			}
		}
		
		Element jar = element.getChild(ConfigStrings.JAR);
		jar.setAttribute(ConfigStrings.NAME, key);
		processor.process(jar, configCreator);
		ProtoPlugin protoPlugin = new ProtoPlugin(dependenciesStringList, key, key);
		configCreator.put(key, protoPlugin);
	}

	@Override
	public String getName() {
		return ConfigStrings.PLUGIN;
	}

}
