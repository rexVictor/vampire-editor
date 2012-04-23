package vampire.editor.plugin;

import java.nio.file.Path;

import vampire.editor.domain.sheet.Sheet;

public interface SheetLoader {
	
	public void loadSheet(Path path, Sheet sheet);

}
