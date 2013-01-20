package vampire.editor.application.startup.configcreator;

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

import vampire.editor.application.startup.configcreator.ConfigCreator;

public class PathProcessorTest {
	
	private Element buildDocument() throws JDOMException, IOException{
		SAXBuilder builder = new SAXBuilder();
		Path path = Paths.get("configtests", "paths","test1.xml");
		Document document = builder.build(path.toFile());
		return document.getRootElement();
	}

	@Test
	public void testPathBuild1() throws JDOMException, IOException {
		ConfigCreator creator = new ConfigCreator();
		PathProcessor processor = new PathProcessor();
		Element root = buildDocument();
		List<Element> paths = root.getChildren("path");
		for(Element e : paths){
			processor.process(e, creator);
		}
		Path expected = Paths.get("first", "second", "third");
		assertEquals(expected, creator.getPath("path1"));
	}
	
	@Test
	public void testPathBuild2() throws JDOMException, IOException {
		ConfigCreator creator = new ConfigCreator();
		PathProcessor processor = new PathProcessor();
		Element root = buildDocument();
		List<Element> paths = root.getChildren("path");
		for(Element e : paths){
			processor.process(e, creator);
		}
		Path expected = Paths.get(System.getProperty("user.home"), "folder", "innerfolder");
		assertEquals(expected, creator.getPath("path2"));
	}
	
	@Test
	public void testPathBuild3() throws JDOMException, IOException {
		ConfigCreator creator = new ConfigCreator();
		PathProcessor processor = new PathProcessor();
		Element root = buildDocument();
		List<Element> paths = root.getChildren("path");
		for(Element e : paths){
			processor.process(e, creator);
		}
		Path expected = Paths.get(System.getProperty("user.home"), "folder", "innerfolder",
				"innerinnerfolder");
		assertEquals(expected, creator.getPath("path3"));
	}
	
	

}
