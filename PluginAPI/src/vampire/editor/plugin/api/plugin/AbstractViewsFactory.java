package vampire.editor.plugin.api.plugin;

import java.util.List;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;

public interface AbstractViewsFactory<M, SM, V, FM> {
	
	public void add(FM module);
	
	public List<V> buildList(M m, ModelToViewModelMapperAPI mapper);
	
	public V build(ModelToViewModelMapperAPI mapper, SM m);
	
}
