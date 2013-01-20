package vampire.editor.persistency.startup;

import java.io.IOException;
import java.nio.file.Path;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLLoader {
	
	public XMLLoader(){
	}
	
	public Document load(Path path) throws XMLImportException{
		Document document;
		SAXBuilder builder = new SAXBuilder();
		try {
			document = builder.build(path.toFile());
		} catch (JDOMException | IOException e) {
			throw new XMLImportException(e);
		}
		return document;
	}

}
