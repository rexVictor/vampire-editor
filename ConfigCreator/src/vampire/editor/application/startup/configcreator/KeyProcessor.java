package vampire.editor.application.startup.configcreator;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.jdom2.Attribute;


class KeyProcessor implements AttributeProcessor<Path>{

	@Override
	public Path process(Path v, ConfigCreator creator, Attribute... attribute) {
		return Paths.get(System.getProperty(attribute[0].getValue()));
	}

	@Override
	public String getName() {
		return "key";
	}

}
