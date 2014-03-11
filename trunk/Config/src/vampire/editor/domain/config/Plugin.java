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

import java.util.Collections;
import java.util.List;

import vampire.editor.plugin.api.plugin.Activator;

public class Plugin {
	
	private final List<Plugin> dependencies;
	
	private final Class<Activator> activator;
	
	private final String key;

	public Plugin(List<Plugin> dependencies, Class<Activator> activator,
			String key) {
		super();
		this.dependencies = Collections.unmodifiableList(dependencies);
		this.activator = activator;
		this.key = key;
	}

	public List<Plugin> getDependencies() {
		return dependencies;
	}

	public Class<Activator> getActivator() {
		return activator;
	}

	public String getKey() {
		return key;
	}
	
}
