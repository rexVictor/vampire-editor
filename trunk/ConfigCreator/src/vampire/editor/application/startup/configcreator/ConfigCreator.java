package vampire.editor.application.startup.configcreator;

import java.awt.Font;
import java.awt.Image;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;

import vampire.editor.domain.config.Config;
import vampire.editor.domain.config.Plugin;
import vampire.editor.persistency.startup.XMLImportException;
import vampire.editor.persistency.startup.XMLLoader;
import vampire.editor.plugin.api.plugin.Activator;

public class ConfigCreator implements ElementProcessor{
	
	private final XMLLoader xmlLoader = new XMLLoader();
	
	private final Map<String, Path> paths = new HashMap<>();
	
	private final Map<String, Font> fonts = new HashMap<>();
	
	private final Map<String, Image> lines = new HashMap<>();
	
	private final Map<String, Image> borders = new HashMap<>();
	
	private final Map<String, Class<Activator>> clazzes = new HashMap<>();
	
	private final Map<String, ElementProcessor> processors = new HashMap<>();
	
	public ConfigCreator() {
		ElementProcessor borderProcessor = new BorderProcessor();
		ElementProcessor fontProcessor = new FontProcessor();
		ElementProcessor jarProcessor = new JarProcessor();
		ElementProcessor lineProcessor = new LineProcessor();
		ElementProcessor pathProcessor = new PathProcessor();
		
		processors.put(borderProcessor.getName(), borderProcessor);
		processors.put(fontProcessor.getName(), fontProcessor);
		processors.put(jarProcessor.getName(), jarProcessor);
		processors.put(lineProcessor.getName(), lineProcessor);
		processors.put(getName(), this);
		processors.put(pathProcessor.getName(), pathProcessor);
	}
	
	public Config loadConfig(Path path) throws ConfigImportException{
		try {
			Document document = xmlLoader.load(path);
			Element root = document.getRootElement();
			process(root);
		} catch (XMLImportException e) {
			throw new ConfigImportException(e);
		}
		Config config = new Config(path, new HashMap<String, Plugin>(), clazzes.remove("gui"),
				clazzes.remove("sheetloader"), fonts, borders, lines);
		return config;
	}
	
	void put(String key, Path path){
		paths.put(key, path);
	}
	
	void put(String key, Font font){
		fonts.put(key, font);
	}
	
	void put(String key, Class<Activator> clazz){
		clazzes.put(key, clazz);
	}
	
	void putLine(String key, Image line){
		lines.put(key, line);
	}
	
	void putBorder(String key, Image line){
		borders.put(key, line);
	}
	
	Font getFont(String key){
		return fonts.get(key);
	}
	
	Path getPath(String key){
		return paths.get(key);
	}
	
	Image getLine(String key){
		return lines.get(key);
	}
	
	Image getBorder(String key){
		return borders.get(key);
	}
	
	private void process(Element root){
		List<Element> children = root.getChildren();
		for(Element e : children){
			String elementName = e.getName();
			ElementProcessor processor = processors.get(elementName);
			processor.process(e, this);
		}
	}
	
	

	@Override
	public void process(Element element, ConfigCreator configCreator) {
		//String configName = element.getAttributeValue("name");
		String pathName = element.getAttributeValue("path");
		String fileName = element.getAttributeValue("file");
		Path configPath = paths.get(pathName).resolve(fileName);
		try {
			Document config = xmlLoader.load(configPath);
			process(config.getRootElement());
		} catch (XMLImportException e) {
			throw new ConfigImportException(e);
		}
	}

	@Override
	public String getName() {
		return "config";
	}

}
