package vampire.editor.application.startup;

import java.nio.file.Path;
import java.nio.file.Paths;

import vampire.editor.application.GeneralController;
import vampire.editor.domain.Config;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String... args) throws Throwable{
		System.out.println(Thread.activeCount());
		Path configPath = Paths.get("resources", "coreconfig.xml");
		ConfigCreator creator = new ConfigCreator(configPath);
		Config config = creator.createConfig();
	//	creator = null;
		//configPath = null;
		new GeneralController(config);
		System.out.println(Thread.activeCount());
		System.out.println("ende");
	}

}
