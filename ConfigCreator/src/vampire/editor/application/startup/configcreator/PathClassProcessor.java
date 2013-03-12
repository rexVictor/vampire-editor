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
