package vampire.editor.application.startup.configcreator;

import java.nio.file.Path;
import java.util.List;

import org.jdom2.Element;


class PathProcessor implements ElementProcessor{
	
	private final SegmentProcessor segmentProcessor = new SegmentProcessor(); 
	
	public PathProcessor() {
		super();
	}

	@Override
	public void process(Element element, ConfigCreator creator) {
		String name = element.getAttributeValue(ConfigStrings.NAME);
		List<Element> children = element.getChildren();
		Path path = null;
		for (Element child : children){
			path = segmentProcessor.process(child, path, creator);
		}
		creator.put(name, path);
	}

	@Override
	public String getName() {
		return ConfigStrings.PATH;
	}

}
