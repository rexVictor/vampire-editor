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

import java.util.List;

public class ProtoPlugin {
	
	private final List<String> dependencies;
	
	private final String activator;
	
	private final String name;


	public ProtoPlugin(List<String> dependencies, String activator, String name) {
		super();
		this.dependencies = dependencies;
		this.activator = activator;
		this.name = name;
	}

	public List<String> getDependencies() {
		return dependencies;
	}

	public String getActivator() {
		return activator;
	}

	public String getName() {
		return name;
	}
	
}