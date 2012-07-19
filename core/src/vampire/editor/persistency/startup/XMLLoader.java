package vampire.editor.persistency.startup;

import java.io.IOException;
import java.nio.file.Path;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLLoader {
	
	private final Path path;
	
	public XMLLoader(Path path){
		this.path = path;
	}
	
	public Document load() throws XMLImportException{
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
