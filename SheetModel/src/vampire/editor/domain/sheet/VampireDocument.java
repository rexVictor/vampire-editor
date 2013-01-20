package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;
import vampire.editor.plugin.api.domain.sheet.VampireDocumentAPI;

public class VampireDocument implements VampireDocumentAPI{
	
	private final Sheet sheet;
	
	private final ModelToViewModelMapper modelToViewModelMapper;
	
	

	public VampireDocument(Sheet sheet,
			ModelToViewModelMapper modelToViewModelMapper) {
		super();
		this.sheet = sheet;
		this.modelToViewModelMapper = modelToViewModelMapper;
	}

	@Override
	public SheetAPI getSheet() {
		return sheet;
	}

	@Override
	public ModelToViewModelMapperAPI getModelToViewModelMapper() {
		return modelToViewModelMapper;
	}

}
