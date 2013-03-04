package vampire.editor.application.startup.configcreator;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.jdom2.Attribute;


class AppendProcessor implements AttributeProcessor<Path>{

	@Override
	public Path process(Path path, ConfigCreator creator, Attribute... attribute) {
		if (path == null){
			return Paths.get(attribute[0].getValue());
		}
		return path.resolve(attribute[0].getValue());
	}

	@Override
	public String getName() {
		return ConfigStrings.APPEND;
	}

}
