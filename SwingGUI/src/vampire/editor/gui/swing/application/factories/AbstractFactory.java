package vampire.editor.gui.swing.application.factories;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.plugin.AbstractViewFactoryModule;

public class AbstractFactory<V, M, FM extends AbstractViewFactoryModule<M, V>> {
	
	protected final List<FM> modules = new LinkedList<>();
	
	public void add(FM module){
		modules.add(module);
	}
	
	protected void callModulesInitial(M m, ModelToViewModelMapperAPI mapper){
		for (FM module : modules){
			module.processInitial(m, mapper);
		}
	}
	
	protected void callModulesFinal(M m, ModelToViewModelMapperAPI mapper, V view){
		for (FM module : modules){
			module.processFinal(m, mapper, view);
		}
	}
	
}
