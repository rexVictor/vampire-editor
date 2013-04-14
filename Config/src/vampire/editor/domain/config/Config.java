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
import vampire.editor.plugin.api.plugin.Activator;

public class Config {
	
	private final Path ownPath;
	
	private final Map<String, Plugin> plugins;
	
	private final Class<Activator> guiPlugin;
	
	private final Map<String, Importer> importers;
	
	private final Map<String, Exporter> exporters;
	
	private final ResourcesHolder holder;

	public Config(Path ownPath, Map<String, Plugin> plugins,
			Class<Activator> guiPlugin, Map<String, Importer> importers,
			Map<String, Exporter> exporters,
			Map<String, Font> fonts, Map<String, Border> borders,
			Map<String, Image> lines, Map<String, Dictionary> dictionaries,
			Map<String, Path> defaultSheets) {
		super();
		this.ownPath = ownPath;
		this.plugins = plugins;
		this.guiPlugin = guiPlugin;
		this.importers = importers;
		this.exporters = exporters;
		holder = new ResourcesHolder(borders, lines, dictionaries, defaultSheets);
		
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
	
	public Map<String, Importer> getImporters(){
		return importers;
	}
	
	public Map<String, Exporter> getExporters(){
		return exporters;
	}
	
	public Map<String, Plugin> getPlugins(){
		return plugins;
	}

}
