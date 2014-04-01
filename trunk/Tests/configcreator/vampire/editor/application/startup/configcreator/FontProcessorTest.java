package vampire.editor.application.startup.configcreator;

import static org.junit.Assert.*;

import java.awt.Font;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;


@SuppressWarnings({"static-method", "nls"})
public class FontProcessorTest {

	private Element initialize() throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Path path = Paths.get("configtests", "fonts","test1.xml");
		Document document = builder.build(path.toFile());
		return document.getRootElement();
	}

	@Test
	public void testFontProcess() throws Exception {
		Element root = initialize();
		ConfigCreator creator = new ConfigCreator();
		Path fontPath = Paths.get("configtests", "fonts");
		creator.put("fonts", fontPath);
		FontProcessor processor = new FontProcessor();
		List<Element> fontElements = root.getChildren("font");
		for(Element e : fontElements){
			processor.process(e, creator);
		}
		Font expected = Font.createFont(0, fontPath.resolve("CAS_ANTN.TTF").toFile());
		Font actual = creator.getFont("cas_antn");
		assertEquals(expected, actual);
	}

}
