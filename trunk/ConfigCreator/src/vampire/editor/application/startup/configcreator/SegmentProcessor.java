package vampire.editor.application.startup.configcreator;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Attribute;
import org.jdom2.Element;


class SegmentProcessor{
	
	private final Map<String, AttributeProcessor<Path>> processors = new HashMap<>();

	public SegmentProcessor() {
		super();
		KeyProcessor keyProcessor = new KeyProcessor();
		AppendProcessor appendProcessor = new AppendProcessor();
		RootProcessor rootProcessor = new RootProcessor();
		processors.put(keyProcessor.getName(), keyProcessor);
		processors.put(appendProcessor.getName(), appendProcessor);
		processors.put(rootProcessor.getName(), rootProcessor);
	}

	public Path process(Element element, Path path, ConfigCreator creator) {
		List<Attribute> attributes = element.getAttributes();
		for(Attribute attribute : attributes){
			String name = attribute.getName();
			path = processors.get(name).process(path, creator, attribute);
		}
		return path;
	}

}
