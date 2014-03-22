package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;

public interface AbstractViewsFactory<M, V, FM> {
	
	public void add(FM module);
	
	public V build(ModelToViewModelMapperAPI mapper, M m);
	
}
