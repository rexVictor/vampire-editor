package vampire.editor.plugin.api.domain.sheet;

import java.nio.file.FileSystem;
import java.nio.file.Path;


public interface VampireDocument extends VampireDocumentAPI{

	public Sheet getSheet();

	public ModelToViewModelMapper getModelToViewModelMapper();

	public Path getPluginDirectory();

	public FileSystem getFileSystem();

	public void setFileSystem(FileSystem fileSystem);

	public Path getPath();

	public void setPath(Path path);

}