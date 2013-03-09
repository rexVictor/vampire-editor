package vampire.editor.sheetloader.application.exporter;

import java.nio.file.Path;

import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;
import vampire.editor.plugin.api.plugin.DocumentExportException;

public class Exporter implements vampire.editor.plugin.api.plugin.SheetExporter{
	
	private final ResourcesHolderAPI resources;
	
	

	public Exporter(ResourcesHolderAPI resources) {
		super();
		this.resources = resources;
	}



	@Override
	public void export(VampireDocumentAPI document, Path path) throws DocumentExportException{
		SheetExporter exporter = new SheetExporter(document, resources);
		exporter.export(path);
	}



	@Override
	public String getFormat() {
		return "vmpcs";
	}

}
