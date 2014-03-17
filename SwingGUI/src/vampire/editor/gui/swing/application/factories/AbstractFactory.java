package vampire.editor.gui.swing.application.factories;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.plugin.AbstractViewFactoryModule;
import vampire.editor.plugin.api.plugin.AbstractViewsFactory;
import vampire.editor.plugin.api.view.sheet.Addable;

public abstract class AbstractFactory<M, SM, SV, V extends Addable<SV>, FM extends AbstractViewFactoryModule<M, V>> {
	
	private final List<FM> modules = new LinkedList<>();
	
	protected final AbstractViewsFactory<M, SM, SV, ?> viewFactory;
	
	
	
	public AbstractFactory(AbstractViewsFactory<M, SM, SV, ?> viewFactory) {
		super();
		this.viewFactory = viewFactory;
	}
	
	protected AbstractFactory(){
		this(null);
	}
	
	public V build(ModelToViewModelMapperAPI mapper, M m){
		Object viewAtts = mapper.getViewAttributes(m);
		V view = constructView(viewAtts, m);
		List<SV> subViews = makeList(m, mapper);
		iteration(subViews, view);
		callModules(m, mapper, view);
		return view;
	}
	
	protected List<SV> makeList(M m, ModelToViewModelMapperAPI mapper){
		return viewFactory.buildList(m, mapper);
	}

	protected void iteration(List<SV> subViews, V view){
		for (SV subView : subViews){
			view.add0(subView);
		}
	}
	
	protected void callModules(M m, ModelToViewModelMapperAPI mapper, V view){
		for (FM module : modules){
			module.process(m, mapper, view);
		}
	}
	
	public void add(FM module){
		modules.add(module);
	}
	
	protected abstract V constructView(Object viewAtts, M m);
	
	
	
}
