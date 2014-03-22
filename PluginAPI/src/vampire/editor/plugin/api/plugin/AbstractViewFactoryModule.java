package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;

public interface AbstractViewFactoryModule<M,V> {
	
	public void processInitial(M m, ModelToViewModelMapperAPI mapper);
	
	public void processFinal(M m, ModelToViewModelMapperAPI mapper, V view);

}
