package vampire.editor.application.startup;

import java.nio.file.Path;
import java.nio.file.Paths;

import vampire.editor.application.GeneralController;
import vampire.editor.application.startup.configcreator.ConfigCreator;
import vampire.editor.domain.config.Config;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String... args) throws Throwable{
		//System.setProperty("javax.xml.parsers.SAXParserFactory", "com.sun.org.apache.xerces.internal.jaxp.SAXParserFactoryImpl");
		Path configPath = Paths.get("resources", "coreconfig.xml");
		ConfigCreator creator = new ConfigCreator();
		Config config = creator.loadConfig(configPath);
		String[] path = new String[]{"..", "Tests", "defaultsheets", "vtmdefault.vmpcs"};
		//String[] path = new String[]{"output.vmpcs"};
		new GeneralController(config, path);
	}

}
