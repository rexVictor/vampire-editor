package vampire.editor.application.startup.configcreator;

import java.io.IOException;
import java.nio.file.Path;

import org.jdom2.Element;

public class DefaultSheetProcessor implements ElementProcessor{

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		String key = element.getAttributeValue(ConfigStrings.NAME);
		String pathName = element.getAttributeValue(ConfigStrings.PATH);
		String fileName = element.getAttributeValue(ConfigStrings.FILE);
		Path path = configCreator.getPath(pathName);
		Path file = path.resolve(fileName);
		try {
			Path toPut = file.toRealPath();
			configCreator.putDefaultSheet(key, toPut);
		} catch (IOException e) {
			throw new ConfigImportException(e);
		}
	}

	@Override
	public String getName() {
		return ConfigStrings.DEFAULT_SHEET;
	}

}
