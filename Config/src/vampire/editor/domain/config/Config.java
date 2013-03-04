package vampire.editor.domain.config;

import java.awt.Font;
import java.awt.Image;
import java.nio.file.Path;
import java.util.Map;

import vampire.editor.domain.Border;
import vampire.editor.plugin.api.plugin.Activator;

public class Config {
	
	private final Path ownPath;
	
	private final Map<String, Plugin> plugins;
	
	private final Class<Activator> guiPlugin;
	
	private final Class<Activator> sheetLoader;
	
	private final ResourcesHolder holder;

	public Config(Path ownPath, Map<String, Plugin> plugins,
			Class<Activator> guiPlugin, Class<Activator> sheetLoader,
			Map<String, Font> fonts, Map<String, Border> borders,
			Map<String, Image> lines, Map<String, Dictionary> dictionaries) {
		super();
		this.ownPath = ownPath;
		this.plugins = plugins;
		this.guiPlugin = guiPlugin;
		this.sheetLoader = sheetLoader;
		holder = new ResourcesHolder(fonts, borders, lines, dictionaries);
		
	}
	
	public ResourcesHolder getResourcesHolder(){
		return holder;
	}
	
	public Path getConigFile(){
		return ownPath;
	}
	
	public Class<Activator> getGUI(){
		return guiPlugin;
	}
	
	public Class<Activator> getLoader(){
		return sheetLoader;
	}
	
	public Map<String, Plugin> getPlugins(){
		return plugins;
	}

}
