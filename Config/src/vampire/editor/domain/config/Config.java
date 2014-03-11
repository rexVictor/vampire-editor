/*******************************************************************************
 * Vampire Editor Config.
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
package vampire.editor.domain.config;

import java.awt.Font;
import java.awt.Image;
import java.nio.file.Path;
import java.util.Map;

import vampire.editor.domain.Border;
import vampire.editor.plugin.api.domain.sheet.ModelConstructors;
import vampire.editor.plugin.api.domain.sheet.view.ViewAttConstructors;
import vampire.editor.plugin.api.plugin.Activator;
/**
 * The Config class for the Vampire Editor.
 * It contains all the information about plugins, importers etc.
 * It is created upon start and needed for the GeneralController to be build.
 * @author rex
 *
 */
public class Config {
	
	/**
	 * The path where the coreconfig.xml is.
	 */
	
	private final Path ownPath;
	
	/**
	 * The Map mapping the name of a plugin to the actual {@link Plugin} Objectj
	 */
	private final Map<String, Plugin> plugins;
	
	/**
	 * The Activator Class Object for the GUI. 
	 */
	private final Class<Activator> guiPlugin;
	
	/**
	 * The map mapping the format of an {@link Importer} to the {@link Importer} itself.
	 */
	
	private final Map<String, Importer> importers;
	
	/**
	 * The map mapping the format of an {@link Exporter} to the {@link Exporter} itself.
	 */
	
	private final Map<String, Exporter> exporters;
	
	/**
	 * The {@link ResourcesHolder}
	 */
	private final ResourcesHolder holder;
	
	/**
	 * The Constructor Object of the ModelImplementation.
	 */
	private final ModelConstructors modelConstructors;
	
	/**
	 * The Constructor Object of the ViewModelImplementation
	 */
	private final ViewAttConstructors viewAttConstructors;
	
	/**
	 * The one and only Constructor for the {@link Config} object. All necessary Information must be provided.
	 * It builds the {@link ResourcesHolder} object itself, with the corresponding parameters.
	 * @param ownPath
	 * @param plugins
	 * @param guiPlugin
	 * @param importers
	 * @param exporters
	 * @param fonts
	 * @param borders
	 * @param lines
	 * @param dictionaries
	 * @param defaultSheets
	 * @param modelConstructors
	 * @param viewAttConstructors
	 */
	public Config(Path ownPath, Map<String, Plugin> plugins,
			Class<Activator> guiPlugin, Map<String, Importer> importers,
			Map<String, Exporter> exporters,
			Map<String, Font> fonts, Map<String, Border> borders,
			Map<String, Image> lines, Map<String, Dictionary> dictionaries,
			Map<String, Path> defaultSheets,
			ModelConstructors modelConstructors,
			ViewAttConstructors viewAttConstructors) {
		super();
		this.ownPath = ownPath;
		this.plugins = plugins;
		this.guiPlugin = guiPlugin;
		this.importers = importers;
		this.exporters = exporters;
		holder = new ResourcesHolder(borders, lines, dictionaries, defaultSheets);
		this.modelConstructors = modelConstructors;
		this.viewAttConstructors = viewAttConstructors;
		
	}
	
	/**
	 * Returns the {@link ResourcesHolder}
	 * @return the {@link ResourcesHolder}
	 */
	public ResourcesHolder getResourcesHolder(){
		return holder;
	}
	
	/**
	 * Returns the path of the coreconfig.xml
	 * @return the path of the coreconfig.xml
	 */
	public Path getConigFile(){
		return ownPath;
	}
	
	/**
	 * Returns the {@link Activator} Class of the GUI.
	 * @return Activator Class of the GUI.
	 */
	public Class<Activator> getGUI(){
		return guiPlugin;
	}
	
	/**
	 * Returns a map, mapping the {@link Importer} formats to the actual {@link Importer} objects.
	 * Note: The map returned is not a clone, due to performing reasons. Any modifications in it, can result in
	 * unexpected behavior. It should only be read.
	 * @return {@link Importer}s as Map
	 */
	public Map<String, Importer> getImporters(){
		return importers;
	}
	
	/**
	 * Returns a map, mapping the {@link Exporter} formats to the actual {@link Exporter} objects.
	 * Note: The map returned is not a clone, due to performing reasons. Any modifications in it, can result in
	 * unexpected behavior. It should only be read.
	 * @return {@link Exporter}s as Map
	 */
	public Map<String, Exporter> getExporters(){
		return exporters;
	}
	
	/**
	 * Returns a map, mapping the {@link Plugin} names to the actual {@link Plugin} objects.
	 * Note: The map returned is not a clone, due to performing reasons. Any modifications in it, can result in
	 * unexpected behavior. It should only be read.
	 * @return {@link Plugin}s as Map
	 */
	public Map<String, Plugin> getPlugins(){
		return plugins;
	}

	/**
	 * Returns the factory object for the model implementation.
	 * @return constructor object of the model implementation
	 */
	public ModelConstructors getModelConstructors() {
		return modelConstructors;
	}

	/**
	 * Returns the factory object for the view model implementation.
	 * @return constructor object of the view model implementation
	 */
	public ViewAttConstructors getViewAttConstructors() {
		return viewAttConstructors;
	}
	
	

}
