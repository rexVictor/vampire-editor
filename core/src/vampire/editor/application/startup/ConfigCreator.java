package vampire.editor.application.startup;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;

import vampire.editor.domain.Config;
import vampire.editor.persistency.startup.XMLImportException;
import vampire.editor.persistency.startup.XMLLoader;
import vampire.editor.plugin.Plugin;
import vampire.editor.plugin.api.plugin.Activator;

public class ConfigCreator {
	
	abstract class ElementProcessor{
		
		protected ElementProcessor(){
			processors.put(getType(), this);
		}
		
		public abstract void process(Element element);
		
		public abstract String getType();
		
	}
	
	class PathProcessor extends ElementProcessor{
		
		private final Map<String, AttributeProcessor> attributeProcessors = new HashMap<>(); 
		
		private abstract class AttributeProcessor{
			
			AttributeProcessor(){
				attributeProcessors.put(getName(), this);
			}
			
			public abstract Path process(Attribute attribute, Path current);
			
			public abstract String getName();
		}
		
		class RootProcessor extends AttributeProcessor{

			@Override
			public Path process(Attribute attribute, Path current) {
				return paths.get(attribute.getValue());
			}

			@Override
			public String getName() {
				return "root";
			}
			
		}
		
		class AppendProcessor extends AttributeProcessor{

			@Override
			public Path process(Attribute attribute, Path current) {
				return current.resolve(attribute.getValue());
			}

			@Override
			public String getName() {
				return "append";
			}
			
		}
		
		class KeyProcessor extends AttributeProcessor{

			@Override
			public Path process(Attribute attribute, Path current) {
				return Paths.get(System.getProperty(attribute.getValue()));
			}

			@Override
			public String getName() {
				return "key";
			}
			
		}
		
		PathProcessor(){
			new RootProcessor();
			new AppendProcessor();
			new KeyProcessor();
		}
		
		@Override
		public void process(Element element) {
			List<Element> segments = element.getChildren("segment");
			Path toBuild = Paths.get(".");
			for (Element segment : segments){
				Attribute attribute = segment.getAttributes().get(0);
				String attributeName = attribute.getName();
				toBuild = attributeProcessors.get(attributeName).process(attribute, toBuild);
			}
			paths.put(element.getAttributeValue("name"), toBuild);
			
			
		}

		@Override
		public String getType() {
			return "path";
		}
		
	}
	
	class ConfigProcessor extends ElementProcessor{

		@Override
		public void process(Element element) {
			if ("config".equals(element.getName())){
				String pathCode = element.getAttributeValue("path");
				String fileName = element.getAttributeValue("file");
				Path path = paths.get(pathCode).resolve(fileName);				
				load(path);
			}
			
		}

		@Override
		public String getType() {
			return "config";
		}
		
	}
	
	class FontProcessor extends ElementProcessor{

		@Override
		public void process(Element element) {
			String pathCode = element.getAttributeValue("path");
			String fileName = element.getAttributeValue("file");
			String key = element.getAttributeValue("key");
			Path path = paths.get(pathCode).resolve(fileName);
			try {
				Font font = Font.createFont(0, path.toFile());
				fonts.put(key, font);
			} catch (FontFormatException | IOException e) {
				throw new IllegalArgumentException(e);
			}
			
			
		}

		@Override
		public String getType() {
			return "font";
		}
		
	}
	
	class JarProcessor extends ElementProcessor{

		@SuppressWarnings("unchecked")
		@Override
		public void process(Element element) {
			String pathCode = element.getAttributeValue("path");
			String fileName = element.getAttributeValue("file");
			String activatorClassName = element.getAttributeValue("class");
			String name = element.getAttributeValue("name");
			Class<Activator> clazz = null;
			if (pathCode == null || fileName == null){
				
				try {
					clazz  = (Class<Activator>) Class.forName(activatorClassName);
				} catch (ClassNotFoundException e) {}
				
			}
			activators.put(name, clazz);
			
		}

		@Override
		public String getType() {
			return "jar";
		}
		
	}
	
	class BorderProcessor extends ElementProcessor{

		@Override
		public void process(Element element) {
			String pathCode = element.getAttributeValue("path");
			String fileName = element.getAttributeValue("file");
			String key = element.getAttributeValue("key");
			Path path = paths.get(pathCode).resolve(fileName);
			try {
				Image border = ImageIO.read(path.toFile());
				borders.put(key, border);
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
			
			
		}

		@Override
		public String getType() {
			return "border";
		}
		
	}
	
	class LineProcessor extends ElementProcessor{

		@Override
		public void process(Element element) {
			String pathCode = element.getAttributeValue("path");
			String fileName = element.getAttributeValue("file");
			String key = element.getAttributeValue("key");
			Path path = paths.get(pathCode).resolve(fileName);
			try {
				Image line = ImageIO.read(path.toFile());
				lines.put(key, line);
			} catch (IOException e) {
				throw new IllegalArgumentException(e);
			}
			
			
		}

		@Override
		public String getType() {
			return "line";
		}
		
	}
	
	private final Map<String, ElementProcessor> processors = new HashMap<>();
	
	private final Map<String, Font> fonts = new HashMap<>();
	
	private final Map<String, Plugin> plugins = new HashMap<>();
	
	private final Map<String, Class<Activator>> activators = new HashMap<>();
	
	private final Map<String, Path> paths = new HashMap<>();
	
	private final Map<String, Image> borders = new HashMap<>();
	
	private final Map<String, Image> lines = new HashMap<>();
	
	private final Path configPath;
	
	public ConfigCreator(Path configPath){
		this.configPath = configPath;
		new FontProcessor();
		new PathProcessor();
		new ConfigProcessor();
		new BorderProcessor();
		new LineProcessor();
		new JarProcessor();
		load(configPath);
	}
	
	
	private void load(Path path) {
		try{
			XMLLoader loader = new XMLLoader(path);
			Document document = loader.load();
			Element root = document.getRootElement();
			List<Element> children = root.getChildren();
			for (Element e : children){
				processors.get(e.getName()).process(e);
			}
		}
		catch (XMLImportException e){
			
		}
		
	}
	
	Map<String, Path> getPaths(){
		return paths;
	}
	
	public Config createConfig(){
		Class<Activator> gui = activators.remove("gui");
		Class<Activator> loader = activators.remove("sheetloader");
		Config config = new Config(configPath, plugins, gui, loader, fonts, borders, lines);
		return config;
	}
	
	

}
