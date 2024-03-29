/*******************************************************************************
 * Vampire Editor Config creator.
 * Copyright (C) 2013  Matthias Johannes Reimchen
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.application.startup.configcreator;

import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;

import vampire.editor.domain.Border;
import vampire.editor.domain.config.Config;
import vampire.editor.domain.config.Dictionary;
import vampire.editor.domain.config.Exporter;
import vampire.editor.domain.config.Importer;
import vampire.editor.domain.config.Plugin;
import vampire.editor.persistency.startup.XMLImportException;
import vampire.editor.persistency.startup.XMLLoader;
import vampire.editor.plugin.api.domain.sheet.ModelConstructors;
import vampire.editor.plugin.api.domain.sheet.view.ViewAttConstructors;
import vampire.editor.plugin.api.plugin.Activator;

public class ConfigCreator implements ElementProcessor{
	
	private final Map<String, Path> paths = new HashMap<>();
	
	private final Map<String, Font> fonts = new HashMap<>();
	
	private final Map<String, Image> lines = new HashMap<>();
	
	private final Map<String, Border> borders = new HashMap<>();
	
	private final Map<String, Class<Activator>> clazzes = new HashMap<>();
	
	private final Map<String, ElementProcessor> processors = new HashMap<>();
	
	private final Map<String, Plugin> plugins = new HashMap<>();
	
	private final Map<String, ProtoPlugin> protoPlugins = new HashMap<>();
	
	private final Map<String, Dictionary> dictionaries = new HashMap<>();
	
	private final Map<String, Path> defaultSheets = new HashMap<>();
	
	private final Map<String, Importer> importers = new HashMap<>();
	
	private final Map<String, Exporter> exporters = new HashMap<>();
	
	private ModelConstructors modelConstructors;
	
	private ViewAttConstructors viewAttConstructors;
	
	
	public ConfigCreator() {
		
		List<Class<? extends ElementProcessor>> processors;
		try {
			processors = PackageIterator.getClassesOfType(ElementProcessor.class, getClass().getPackage().toString().substring(8));
			for (Class<? extends ElementProcessor> c: processors){
				if (c.equals(this.getClass())){
					this.processors.put(this.getName(), this);
					continue;
				}
					
				if (c.isInterface())
					continue;
				ElementProcessor processor = c.newInstance();
				this.processors.put(processor.getName(), processor);
			}
		} catch (ClassNotFoundException | IOException | URISyntaxException 
				| InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		/*ElementProcessor borderProcessor = new BorderProcessor();
		ElementProcessor fontProcessor = new FontProcessor();
		ElementProcessor jarProcessor = new JarProcessor();
		ElementProcessor lineProcessor = new LineProcessor();
		ElementProcessor pathProcessor = new PathProcessor();
		ElementProcessor pluginProcessor = new PluginProcessor();
		ElementProcessor localeProcessor = new LocaleProcessor();
		ElementProcessor dictionaryProcessor = new DictionaryProcessor();
		ElementProcessor defaultSheetProcessor = new DefaultSheetProcessor();
		ElementProcessor importerProcessor = new ImporterProcessor();
		ElementProcessor exporterProcessor = new ExporterProcessor();
		
		processors.put(borderProcessor.getName(), borderProcessor);
		processors.put(fontProcessor.getName(), fontProcessor);
		processors.put(jarProcessor.getName(), jarProcessor);
		processors.put(lineProcessor.getName(), lineProcessor);
		processors.put(getName(), this);
		processors.put(pathProcessor.getName(), pathProcessor);
		processors.put(pluginProcessor.getName(), pluginProcessor);
		processors.put(localeProcessor.getName(), localeProcessor);
		processors.put(dictionaryProcessor.getName(), dictionaryProcessor);
		processors.put(defaultSheetProcessor.getName(), defaultSheetProcessor);
		processors.put(importerProcessor.getName(), importerProcessor);
		processors.put(exporterProcessor.getName(), exporterProcessor);*/
	}
	
	public Config loadConfig(Path path) throws ConfigImportException{
		try {
			Document document = XMLLoader.load(path);
			Element root = document.getRootElement();
			process(root);
		} catch (XMLImportException e) {
			throw new ConfigImportException(e);
		}
		makePlugins();
		Config config = new Config(path, plugins, clazzes.remove(ConfigStrings.GUI),
				importers, exporters, fonts, borders, lines, dictionaries,
				defaultSheets, modelConstructors, viewAttConstructors);
		return config;
	}
	
	private void makePlugins(){
		Set<String> protoPluginKeys = protoPlugins.keySet();
		int preSize = protoPluginKeys.size();
		int postSize = protoPluginKeys.size();
		while (postSize != 0){
			preSize = protoPluginKeys.size();
			for (Iterator<String> i = protoPluginKeys.iterator();i.hasNext();){
				String s = i.next();
				ProtoPlugin protoPlugin = protoPlugins.get(s);
				List<String> dependencies = protoPlugin.getDependencies();
				Set<String> pluginKeys = plugins.keySet();
				if (pluginKeys.containsAll(dependencies)){
					List<Plugin> plugins = new LinkedList<>();
					for (String dependency : dependencies){
						plugins.add(this.plugins.get(dependency));
					}
					Plugin plugin = new Plugin(plugins, clazzes.get(protoPlugin.getActivator()), protoPlugin.getName());
					this.plugins.put(protoPlugin.getName(), plugin);
					i.remove();
				}
			}
			postSize = protoPluginKeys.size();
			if (postSize == preSize)
				throw new ConfigImportException("Error in dependencies"); //$NON-NLS-1$
		}
		
	}
	
	void put(String key, Exporter exporter){
		exporters.put(key, exporter);
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
	
	void put(String key, Plugin plugin){
		plugins.put(key, plugin);
	}
	
	void put(String key, ProtoPlugin protoPlugin){
		protoPlugins.put(key, protoPlugin);
	}
	
	ProtoPlugin getProtoPlugin(String key){
		return protoPlugins.get(key);
	}
	
	void putLine(String key, Image line){
		lines.put(key, line);
	}
	
	void putBorder(String key, Border border){
		borders.put(key, border);
	}
	
	void put(String key, Dictionary dictionary){
		dictionaries.put(key, dictionary);
	}
	
	void putDefaultSheet(String key, Path path){
		defaultSheets.put(key, path);
	}
	
	void put(String key, Importer protoImporter){
		importers.put(key, protoImporter);
	}
	
	Dictionary getDictionary(String key){
		return dictionaries.get(key);
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
	
	Border getBorder(String key){
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
		String pathName = element.getAttributeValue(ConfigStrings.PATH);
		String fileName = element.getAttributeValue(ConfigStrings.FILE);
		Path configPath = paths.get(pathName).resolve(fileName);
		try {
			Document config = XMLLoader.load(configPath);
			process(config.getRootElement());
		} catch (XMLImportException e) {
			throw new ConfigImportException(e);
		}
	}
	
	

	public void setModelConstructors(ModelConstructors modelConstructors) {
		this.modelConstructors = modelConstructors;
	}

	public void setViewAttConstructors(ViewAttConstructors viewAttConstructors) {
		this.viewAttConstructors = viewAttConstructors;
	}

	@Override
	public String getName() {
		return ConfigStrings.CONFIG;
	}

}
