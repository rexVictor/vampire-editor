package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.SMetaEntryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.view.MetaEntryViewAttributesAPI;
import vampire.editor.plugin.api.plugin.view.factories.MetaEntryViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.MetaEntryViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.MetaEntryView;

public class SMetaEntryViewFactory extends AbstractFactory<MetaEntryView, MetaEntryAPI, MetaEntryViewFactoryModule> 
				implements MetaEntryViewFactory{
	
	public SMetaEntryViewFactory(DictionaryAPI dictionary) {
		super(dictionary);
	}

	@Override
	public SMetaEntryView build(ModelToViewModelMapperAPI mapper, MetaEntryAPI meta){
		callModulesInitial(meta, mapper);
		SMetaEntryView view = new SMetaEntryView(dictionary, (MetaEntryViewAttributesAPI) mapper.getViewAttributes(meta));
		view.setTitle(meta.getName());
		view.setContent(meta.getValue());
		callModulesFinal(meta, mapper, view);
		return view;
	}

}
