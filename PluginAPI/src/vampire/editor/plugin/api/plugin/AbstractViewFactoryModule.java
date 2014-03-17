package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;

public interface AbstractViewFactoryModule<M,V> {
	
	public void process(M m, ModelToViewModelMapperAPI mapper, V view);

}
