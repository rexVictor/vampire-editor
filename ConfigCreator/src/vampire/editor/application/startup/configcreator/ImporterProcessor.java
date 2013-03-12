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

import org.jdom2.Element;

class ImporterProcessor implements ElementProcessor{
	
	private final JarProcessor processor = new JarProcessor();

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String name = element.getAttributeValue(ConfigStrings.NAME);
		String format = element.getAttributeValue(ConfigStrings.FORMAT);
		Element jar = element.getChild(ConfigStrings.JAR);
		jar.setAttribute(ConfigStrings.NAME, name);
		processor.process(jar, configCreator);
		ProtoImporter importer = new ProtoImporter(name, name, format);
		configCreator.put(name, importer);
	}

	@Override
	public String getName() {
		return ConfigStrings.IMPORTER;
	}

}
