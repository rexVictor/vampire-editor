package vampire.editor.application.startup.configcreator;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Locale;
import java.util.ResourceBundle;

import org.jdom2.Element;

import vampire.editor.domain.config.Dictionary;

class DictionaryProcessor implements ElementProcessor{

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String pathKey = element.getAttributeValue(ConfigStrings.PATH);
		Path path = configCreator.getPath(pathKey);
		try {
			URL url = path.toUri().toURL();
			URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
			Locale locale = Locale.getDefault();
			String baseValue = element.getAttributeValue(ConfigStrings.KEY);
			ResourceBundle bundle = ResourceBundle.getBundle(baseValue, locale, classLoader);
			Dictionary dictionary = new Dictionary(bundle, baseValue);
			configCreator.put(baseValue, dictionary);
		} catch (MalformedURLException e) {
			throw new ConfigImportException(e);
		}
	}

	@Override
	public String getName() {
		return ConfigStrings.DICTIONARY;
	}

}
