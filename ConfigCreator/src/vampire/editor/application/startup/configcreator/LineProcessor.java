package vampire.editor.application.startup.configcreator;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import org.jdom2.Element;

class LineProcessor implements ElementProcessor{

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String pathName = element.getAttributeValue(ConfigStrings.PATH);
		String fileName = element.getAttributeValue(ConfigStrings.FILE);
		String key = element.getAttributeValue(ConfigStrings.KEY);
		Path linePath = configCreator.getPath(pathName).resolve(fileName);
		try {
			Image line = ImageIO.read(linePath.toFile());
			configCreator.putLine(key, line);
		} catch (IOException e) {
			throw new ConfigImportException(e);
		}
	}

	@Override
	public String getName() {
		return ConfigStrings.LINE;
	}

}
