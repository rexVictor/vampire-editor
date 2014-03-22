package vampire.editor.gui.swing.application.factories;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import vampire.editor.gui.swing.view.traitviews.AbstractTraitView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributes;
import vampire.editor.plugin.api.plugin.TraitViewFactory;
import vampire.editor.plugin.api.plugin.TraitViewFactoryModule;
import vampire.editor.plugin.api.plugin.ValueViewFactory;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class STraitViewFactory extends AbstractFactory<TraitView, TraitAPI, TraitViewFactoryModule>
			implements TraitViewFactory{
	
	private final DictionaryAPI dictionary;
	
	private final List<TraitViewFactoryModule> modules = new LinkedList<>();
	
	private final ValueViewFactory valueViewFactory;
	
	
	public STraitViewFactory(DictionaryAPI dictionary, ValueViewFactory valueViewFactory) {
		super();
		this.dictionary = dictionary;
		this.valueViewFactory = valueViewFactory;
	}

	public void addModule(TraitViewFactoryModule module){
		modules.add(module);
	}
	
	public List<TraitView> buildList(SubCategoryAPI traits, ModelToViewModelMapperAPI mapper){
		List<TraitView> traitViews = new ArrayList<>();
		for (Iterator<? extends TraitAPI> i = traits.getIterator(); i.hasNext();) {
			TraitAPI trait = i.next();
			traitViews.add(build(mapper, trait));
		}
		return traitViews;
	}
	
	public TraitView build(ModelToViewModelMapperAPI mapper, TraitAPI trait){
		ValueView valueView = valueViewFactory.build(trait.getValue(), mapper);
		AbstractTraitView traitView = AbstractTraitView.buildTraitView(valueView, 
				(TraitViewAttributes) mapper.getViewAttributes(trait), dictionary);
		traitView.setName(trait.getName());
		return traitView;
	}

}
