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

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.jdom2.Element;

import vampire.editor.domain.Border;

class BorderProcessor implements ElementProcessor{

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String pathName = element.getAttributeValue(ConfigStrings.PATH);
		String fileName = element.getAttributeValue(ConfigStrings.FILE);
		String key = element.getAttributeValue(ConfigStrings.KEY);
		Path borderPath = configCreator.getPath(pathName).resolve(fileName);
		try {
			int left = Integer.parseInt(element.getAttributeValue(ConfigStrings.LEFT));
			int right = Integer.parseInt(element.getAttributeValue(ConfigStrings.RIGHT));
			int top = Integer.parseInt(element.getAttributeValue(ConfigStrings.TOP));
			int bottom = Integer.parseInt(element.getAttributeValue(ConfigStrings.BOTTOM));
			int width = Integer.parseInt(element.getAttributeValue(ConfigStrings.SHEET_WIDTH));
			int height = Integer.parseInt(element.getAttributeValue(ConfigStrings.SHEET_HEIGHT));
			Image borderImage = ImageIO.read(borderPath.toFile());
			Border border = new Border(borderImage, left, right, top, bottom, width, height);
			configCreator.putBorder(key, border);
		} catch (IOException | NumberFormatException e) {
			throw new ConfigImportException(e);
		}
	}

	@Override
	public String getName() {
		return ConfigStrings.BORDER;
	}

}

