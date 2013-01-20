package vampire.editor.application.startup.configcreator;

import org.jdom2.Attribute;

interface AttributeProcessor<V> {
	
	V process(V v, ConfigCreator creator, Attribute... attribute);
	
	String getName();

}
