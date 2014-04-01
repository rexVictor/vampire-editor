package vampire.editor.exporter.vmpcs.application;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import vampire.editor.fileformat.vmpcs.domain.StringConstants;
import vampire.editor.plugin.api.domain.sheet.VampireDocument;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.exporter.DocumentExportException;
import vampire.editor.plugin.api.exporter.SheetExporter;

public class Exporter implements SheetExporter{
	
	private final VMPCSExporter vmpcsExporter = new VMPCSExporter();

	@Override
	public void export(VampireDocumentAPI document, Path path)
			throws DocumentExportException {
		try {
			if (Files.exists(path)) Files.delete(path);
			vmpcsExporter.export((VampireDocument) document, path);
		} catch (IOException | URISyntaxException e) {
			throw new DocumentExportException(e);
		}
	}

	@Override
	public String getFormat() {
		return StringConstants.FORMAT;
	}

}
