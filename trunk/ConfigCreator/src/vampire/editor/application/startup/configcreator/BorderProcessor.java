package vampire.editor.application.startup.configcreator;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.jdom2.Element;

class BorderProcessor implements ElementProcessor{

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String pathName = element.getAttributeValue("path");
		String fileName = element.getAttributeValue("file");
		String key = element.getAttributeValue("key");
		Path borderPath = configCreator.getPath(pathName).resolve(fileName);
		try {
			Image border = ImageIO.read(borderPath.toFile());
			configCreator.putBorder(key, border);
		} catch (IOException e) {
			throw new ConfigImportException(e);
		}
	}

	@Override
	public String getName() {
		return "border";
	}

}

