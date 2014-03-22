package vampire.editor.gui.swing.application.factories;

import java.util.Iterator;

import vampire.editor.plugin.api.domain.sheet.HasIterator;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.plugin.AbstractNonLeafViewFactoryModule;
import vampire.editor.plugin.api.plugin.AbstractViewsFactory;
import vampire.editor.plugin.api.view.sheet.Addable;

public abstract class NonLeafAbstractFactory<M extends HasIterator<SM>, SM, SV, V extends Addable<SV>,
							FM extends AbstractNonLeafViewFactoryModule<M, V, SV>>
							extends AbstractFactory<V, M, FM>{
	
	protected final AbstractViewsFactory<SM, SV, ?> viewFactory;
	
	public NonLeafAbstractFactory(AbstractViewsFactory<SM, SV, ?> viewFactory) {
		super();
		this.viewFactory = viewFactory;
	}
	
	protected NonLeafAbstractFactory(){
		this(null);
	}
	
	public V build(ModelToViewModelMapperAPI mapper, M m){
		callModulesInitial(m, mapper);
		Object viewAtts = mapper.getViewAttributes(m);
		V view = constructView(viewAtts, m);
		makeList(m, mapper, view);
		callModulesFinal(m, mapper, view);
		return view;
	}
	
	protected void makeList(M m, ModelToViewModelMapperAPI mapper, V view){
		for (Iterator<? extends SM> i = m.getIterator(); i.hasNext();){
			SM subModel = i.next();
			SV subView = viewFactory.build(mapper, subModel);
			for (FM module : modules){
				module.addToChild(m, mapper, subView);
			}
			view.add0(subView);
		}
	}
	
	protected abstract V constructView(Object viewAtts, M m);
	
	
	
}
