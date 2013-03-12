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

import org.jdom2.Attribute;
import org.jdom2.Element;

import vampire.editor.plugin.api.plugin.Activator;

class JarProcessor implements ElementProcessor{
	
	private final NullProcessor nullProcessor = new NullProcessor();
	private final PathClassProcessor pathClassProcessor = new PathClassProcessor();

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String name = element.getAttributeValue(ConfigStrings.NAME);
		Attribute path = element.getAttribute(ConfigStrings.PATH);
		Attribute file = element.getAttribute(ConfigStrings.FILE);
		ClassLoader loader = null;
		if(path == null){
			loader = nullProcessor.process(null, null, null, null);
		}
		else {
			loader = pathClassProcessor.process(null, configCreator, path, file);
		}
		String clazzname = element.getAttributeValue(ConfigStrings.CLASS);
		try {
			@SuppressWarnings("unchecked")
			Class<Activator> clazz = (Class<Activator>) loader.loadClass(clazzname);
			configCreator.put(name, clazz);
		} catch (ClassNotFoundException e) {
			throw new ConfigImportException(e);
		}
	}

	@Override
	public String getName() {
		return ConfigStrings.JAR;
	}

}
