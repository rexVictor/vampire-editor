package vampire.editor.gui.swing.application.factories;


import vampire.editor.gui.swing.view.traitviews.AbstractTraitView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;
import vampire.editor.plugin.api.plugin.view.factories.TraitViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.TraitViewFactoryModule;
import vampire.editor.plugin.api.plugin.view.factories.ValueViewFactory;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class STraitViewFactory extends AbstractFactory<TraitView, TraitAPI, TraitViewFactoryModule>
			implements TraitViewFactory{
	
	private final ValueViewFactory valueViewFactory;
	
	
	public STraitViewFactory(DictionaryAPI dictionary, ValueViewFactory valueViewFactory) {
		super(dictionary);
		this.valueViewFactory = valueViewFactory;
	}

	public void addModule(TraitViewFactoryModule module){
		modules.add(module);
	}
	
	@Override
	public TraitView build(ModelToViewModelMapperAPI mapper, TraitAPI trait){
		ValueView valueView = valueViewFactory.build(trait.getValue(), mapper);
		AbstractTraitView traitView = AbstractTraitView.buildTraitView(valueView, 
				(TraitViewAttributes) mapper.getViewAttributes(trait), dictionary);
		traitView.setName(trait.getName());
		return traitView;
	}

}
