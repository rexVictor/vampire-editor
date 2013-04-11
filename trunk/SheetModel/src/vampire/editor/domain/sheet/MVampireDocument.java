/*******************************************************************************
 * Vampire Editor Model Implementation.
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
package vampire.editor.domain.sheet;

import java.nio.file.FileSystem;
import java.nio.file.Path;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapper;
import vampire.editor.plugin.api.domain.sheet.Sheet;
import vampire.editor.plugin.api.domain.sheet.VampireDocument;

/**
 * This class holds a {@link MSheet} and the {@link MModelToViewModelMapper},
 * therefore providing all the information available of a document.
 * @author rex_victor
 *
 */
class MVampireDocument implements VampireDocument{
	
	private final Sheet sheet;
	
	private final ModelToViewModelMapper modelToViewModelMapper;
	
	private final Path pluginDirectory;
	
	private FileSystem fileSystem;
	
	private Path path;
	
	

	public MVampireDocument(Sheet sheet,
			ModelToViewModelMapper modelToViewModelMapper,
			Path pluginDirectory) {
		super();
		this.sheet = sheet;
		this.modelToViewModelMapper = modelToViewModelMapper;
		this.pluginDirectory = pluginDirectory;
	}

	@Override
	public Sheet getSheet() {
		return sheet;
	}

	@Override
	public ModelToViewModelMapper getModelToViewModelMapper() {
		return modelToViewModelMapper;
	}

	@Override
	public Path getPluginDirectory() {
		return pluginDirectory;
	}

	@Override
	public FileSystem getFileSystem() {
		return fileSystem;
	}

	public void setFileSystem(FileSystem fileSystem) {
		this.fileSystem = fileSystem;
	}

	@Override
	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

}
