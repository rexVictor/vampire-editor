package vampire.editor.application.startup.configcreator;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;

import org.jdom2.Attribute;


class PathClassProcessor implements AttributeProcessor<ClassLoader>{

	@Override
	public ClassLoader process(ClassLoader v, ConfigCreator creator,
			Attribute... attribute) {
		String pathName = attribute[0].getValue();
		String fileName = attribute[1].getValue();
		Path jarPath = creator.getPath(pathName).resolve(fileName);
		try {
			URL jarURL = jarPath.toUri().toURL();
			URL[] urls = new URL[]{jarURL};
			URLClassLoader classLoader = new URLClassLoader(urls);
			return classLoader;
		} catch (MalformedURLException e) {
			throw new ConfigImportException(e);
		}
	}

	@Override
	public String getName() {
		return ConfigStrings.PATH;
	}

}
