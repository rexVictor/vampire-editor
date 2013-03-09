package vampire.editor.plugin.api.plugin;

import java.nio.file.Path;

import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;

public interface SheetExporter {
	
	public void export(VampireDocumentAPI document, Path path) throws DocumentExportException;
	
	public String getFormat();

}
