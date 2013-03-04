package vampire.editor.plugin.api.plugin;

import java.nio.file.Path;

import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;

public interface SheetImporter {

	public abstract VampireDocumentAPI loadDocument(Path path);

}