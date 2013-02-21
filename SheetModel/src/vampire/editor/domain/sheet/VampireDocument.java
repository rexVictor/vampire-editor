package vampire.editor.domain.sheet;

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
	public Sheet getSheet() {
		return sheet;
	}

	@Override
	public ModelToViewModelMapper getModelToViewModelMapper() {
		return modelToViewModelMapper;
	}

}
