package vampire.editor.application.startup.configcreator;

import org.jdom2.Element;

interface ElementProcessor {
	
	void process(Element element, ConfigCreator configCreator);
	
	String getName();

}
