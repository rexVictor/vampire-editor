package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.SMeritEntryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.MeritAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttibutesAPI;
import vampire.editor.plugin.api.plugin.view.factories.MeritEntryViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.MeritEntryViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;

public class SMeritEntryViewFactory extends AbstractFactory<MeritEntryView, MeritAPI, MeritEntryViewFactoryModule>
									implements MeritEntryViewFactory{
	
	public SMeritEntryViewFactory(DictionaryAPI dictionary) {
		super(dictionary);
	}

	@Override
	public SMeritEntryView build(ModelToViewModelMapperAPI mapper, MeritAPI merit){
		callModulesInitial(merit, mapper);
		SMeritEntryView view = new SMeritEntryView(dictionary, (MeritEntryViewAttibutesAPI) mapper.getViewAttributes(merit));
		view.setCost(merit.getCost());
		view.setText(merit.getName());
		callModulesFinal(merit, mapper, view);
		return view;
	}

}
