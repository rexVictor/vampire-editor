package vampire.editor.plugin.api.domain;

import java.nio.file.Path;
import java.nio.file.Paths;

@SuppressWarnings("nls")
public class PathConstants {
	
	public static final Path RESOURCES = Paths.get("resources");
	
	public static final Path CORECONFIG = RESOURCES.resolve("coreconfig.xml");

}
