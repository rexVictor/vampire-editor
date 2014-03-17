package vampire.editor.gui.swing.application;

import java.util.List;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;

public class AbstractFactory<VIEW, MODEL, VIEWATTS, SUBVIEW, SUBVIEWFACTOTY> {
	
	private final AbstractFactory<SUBVIEW, ?, ? , ?, ?> subFactory;
	
	public AbstractFactory(AbstractFactory<SUBVIEW, ?, ?, ?, ?> subViewFactoty) {
		super();
		this.subFactory = subViewFactoty;
	}



	public VIEW build(MODEL model, ModelToViewModelMapperAPI mapper){
		@SuppressWarnings("unchecked")
		VIEWATTS viewAtts = (VIEWATTS) mapper.getViewAttributes(model);
		return null;
	}
	

}
