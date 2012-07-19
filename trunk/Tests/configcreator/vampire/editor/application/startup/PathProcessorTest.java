package vampire.editor.application.startup;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;

import vampire.editor.application.startup.ConfigCreator.PathProcessor;

public class PathProcessorTest {

	@Test
	public void testProcess() throws JDOMException, IOException {
		ConfigCreator creator = new ConfigCreator(null);
		PathProcessor processor = creator.new PathProcessor();
		SAXBuilder builder = new SAXBuilder();
		Path path = Paths.get("configtests", "paths", "test1.xml");
		Document document = builder.build(path.toFile());
		List<Element> elements = document.getRootElement().getChildren("path");
		for (Element e : elements){
			processor.process(e);
		}
		System.out.println(creator.getPaths());
		fail("not yet implemented");
		
	}
	
	

	@Test
	public void testGetType() {
		PathProcessor processor = new ConfigCreator(null).new PathProcessor();
		assertEquals("path", processor.getType());
	}

}
