package vampire.editor.application.startup.configcreator;

import java.nio.file.Path;

import org.jdom2.Attribute;


class RootProcessor implements AttributeProcessor<Path>{

	@Override
	public Path process(Path v, ConfigCreator creator, Attribute... attribute) {
		String name = attribute[0].getValue();
		return creator.getPath(name);
	}

	@Override
	public String getName() {
		return ConfigStrings.ROOT;
	}

}
