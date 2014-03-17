package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;

public interface TraitViewFactoryModule {
	
	public void process(TraitAPI trait, ModelToViewModelMapperAPI mapper);
	
}
