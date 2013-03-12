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

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Attribute;
import org.jdom2.Element;


class SegmentProcessor{
	
	private final Map<String, AttributeProcessor<Path>> processors = new HashMap<>();

	public SegmentProcessor() {
		super();
		KeyProcessor keyProcessor = new KeyProcessor();
		AppendProcessor appendProcessor = new AppendProcessor();
		RootProcessor rootProcessor = new RootProcessor();
		processors.put(keyProcessor.getName(), keyProcessor);
		processors.put(appendProcessor.getName(), appendProcessor);
		processors.put(rootProcessor.getName(), rootProcessor);
	}

	public Path process(Element element, Path path, ConfigCreator creator) {
		List<Attribute> attributes = element.getAttributes();
		for(Attribute attribute : attributes){
			String name = attribute.getName();
			path = processors.get(name).process(path, creator, attribute);
		}
		return path;
	}

}
