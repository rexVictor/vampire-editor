package vampire.editor.application.startup.configcreator;

import org.jdom2.Attribute;


class NullProcessor implements AttributeProcessor<ClassLoader> {

	@Override
	public ClassLoader process(ClassLoader v, ConfigCreator creator,
			Attribute... attribute) {
		return getClass().getClassLoader();
	}

	@Override
	public String getName() {
		return null;
	}

}
