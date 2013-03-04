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

