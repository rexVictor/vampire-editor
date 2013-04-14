/*******************************************************************************
 * An importer for the vampire editor handling vmpcs format.
 * Copyright (C) 2013 Matthias Reimchen
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
 * For further information see https://code.google.com/p/vampire-editor/ or write a
 * mail to development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.importer.vmpcs.persistency;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileUtils {
	
	public static void copyAll(Path source, Path target) throws IOException{
		DirectoryStream<Path> stream = Files.newDirectoryStream(source);
		for (Path sourceFile : stream){
			Path targetFile = target.resolve(sourceFile.getFileName().toString());
			Files.copy(sourceFile, targetFile);
			if (Files.isDirectory(sourceFile)){
				copyAll(sourceFile, targetFile);
			}
		}
		stream.close();
	}
	
	public static void deleteAll(Path toDelete) throws IOException{
		DirectoryStream<Path> stream = Files.newDirectoryStream(toDelete);
		for (Path p : stream){
			if (Files.isDirectory(p)){
				deleteAll(p);
			}
			Files.delete(p);
		}
		Files.delete(toDelete);
		stream.close();
	}

}
