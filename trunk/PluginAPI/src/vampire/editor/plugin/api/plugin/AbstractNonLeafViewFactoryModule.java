package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;

public interface AbstractNonLeafViewFactoryModule<M, V, SV> extends AbstractViewFactoryModule<M, V>{
	
	public void addToChild(M m, ModelToViewModelMapperAPI mapper, SV subView);

}
