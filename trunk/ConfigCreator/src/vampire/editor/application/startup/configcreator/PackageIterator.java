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

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class PackageIterator {
	
	public static <C> List<Class<? extends C>> getClassesOfType(Class<C> clazz,
			String packageName) throws IOException, URISyntaxException, ClassNotFoundException{
		List<Class<? extends C>> classes = new LinkedList<>();
		String resource = packageName.replace(".", "/");
		URL url = new PackageIterator().getClass().getClassLoader().getResource(resource);
		Path path = Paths.get(url.toURI());
		loadClasses(path, path, clazz, packageName, classes);
		return classes;
	}
	
	@SuppressWarnings("unchecked")
	private static <C> void loadClasses(Path path, Path origin, Class<C> assignable,
			String packageName, List<Class<? extends C>> classes) throws IOException, ClassNotFoundException{
		DirectoryStream<Path> stream = Files.newDirectoryStream(path);
		for (Path p : stream){
			Path relative = origin.relativize(p);
			String className = packageName+"."+relative.toString().replace("/", ".");
			className = className.substring(0, className.length()-6);
			Class<?> clazz = Class.forName(className);
			if (assignable.isAssignableFrom(clazz))
				classes.add((Class<? extends C>) clazz);
		}
	}

}
