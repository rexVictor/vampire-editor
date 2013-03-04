package vampire.editor.application.startup.configcreator;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.nio.file.Path;

import org.jdom2.Element;

class FontProcessor implements ElementProcessor{

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String pathName = element.getAttributeValue(ConfigStrings.PATH);
		String fileName = element.getAttributeValue(ConfigStrings.FILE);
		String key = element.getAttributeValue(ConfigStrings.KEY);
		Path fontPath = configCreator.getPath(pathName).resolve(fileName);
		try {
			Font font = Font.createFont(0, fontPath.toFile());
			configCreator.put(key, font);			
		} catch (FontFormatException | IOException e) {
			throw new ConfigImportException(e);
		}
	}

	@Override
	public String getName() {
		return ConfigStrings.FONT;
	}

}
