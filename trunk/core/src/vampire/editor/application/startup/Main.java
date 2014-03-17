package vampire.editor.application.startup;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import vampire.editor.application.GeneralController;
import vampire.editor.application.startup.configcreator.ConfigCreator;
import vampire.editor.domain.config.Config;
import vampire.editor.plugin.Manager;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String... args) throws Throwable{
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		Path fontPath = Paths.get("resources", "fonts", "CAS_ANTN.TTF");
		InputStream stream = Files.newInputStream(fontPath);
		environment.registerFont(Font.createFont(Font.TRUETYPE_FONT, stream));
		stream.close();
		Path configPath = Paths.get("resources", "coreconfig.xml");
		ConfigCreator creator = new ConfigCreator();
		Config config = creator.loadConfig(configPath);
		GeneralController generalController = new GeneralController(config);
		Manager manager = generalController.getManager();
		for (String s : args){
			Path path = Paths.get(s);
			manager.open(path);
		}
	}

}
