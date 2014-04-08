package vampire.editor.plugin.tooltipadder;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.ResourceBundle;

public class Loader {
	
	private static final String path = "resources/plugins/TooltipAdder/"; //$NON-NLS-1$
	
	@SuppressWarnings("resource")
	public static ResourceBundle load(){
		Path p = Paths.get(path);
		URL url;
		try {
			url = p.toUri().toURL();
			URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
			ResourceBundle bundle = ResourceBundle.getBundle("tooltips", Locale.getDefault(), classLoader); //$NON-NLS-1$
			return bundle;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
