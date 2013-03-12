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

import java.util.LinkedList;
import java.util.List;

import org.jdom2.Element;

public class PluginProcessor implements ElementProcessor{
	
	private final JarProcessor processor = new JarProcessor();

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String key = element.getAttributeValue(ConfigStrings.NAME);
		List<String> dependenciesStringList = new LinkedList<>();
		Element dependenciesElement = element.getChild(ConfigStrings.DEPENDENCIES);
		if (dependenciesElement != null){
			List<Element> dependencies = dependenciesElement.getChildren(ConfigStrings.DEPENDS);
			for (Element e : dependencies){
				dependenciesStringList.add(e.getAttributeValue(ConfigStrings.NAME));
			}
		}
		
		Element jar = element.getChild(ConfigStrings.JAR);
		jar.setAttribute(ConfigStrings.NAME, key);
		processor.process(jar, configCreator);
		ProtoPlugin protoPlugin = new ProtoPlugin(dependenciesStringList, key, key);
		configCreator.put(key, protoPlugin);
	}

	@Override
	public String getName() {
		return ConfigStrings.PLUGIN;
	}

}
