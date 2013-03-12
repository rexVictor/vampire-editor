/*******************************************************************************
 * Vampire Editor Config creator.
 * Copyright (C) 2013  Matthias Johannes Reimchen
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
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
