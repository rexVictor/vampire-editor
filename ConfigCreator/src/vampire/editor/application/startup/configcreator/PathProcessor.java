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
import java.util.List;

import org.jdom2.Element;


class PathProcessor implements ElementProcessor{
	
	private final SegmentProcessor segmentProcessor = new SegmentProcessor(); 
	
	public PathProcessor() {
		super();
	}

	@Override
	public void process(Element element, ConfigCreator creator) {
		String name = element.getAttributeValue(ConfigStrings.NAME);
		List<Element> children = element.getChildren();
		Path path = null;
		for (Element child : children){
			path = segmentProcessor.process(child, path, creator);
		}
		creator.put(name, path);
	}

	@Override
	public String getName() {
		return ConfigStrings.PATH;
	}

}
