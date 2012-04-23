package vampire.editor.plugin;

import java.nio.file.Path;

public interface FileFormat {
	
	public boolean canHandle(Path path);
	
	

}
