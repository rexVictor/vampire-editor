package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.SMeritEntryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.MeritAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttibutesAPI;
import vampire.editor.plugin.api.plugin.MeritEntryViewFactory;
import vampire.editor.plugin.api.plugin.MeritEntryViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;

public class SMeritEntryViewFactory extends AbstractFactory<MeritEntryView, MeritAPI, MeritEntryViewFactoryModule>
									implements MeritEntryViewFactory{
	
	private final DictionaryAPI dictionary;

	public SMeritEntryViewFactory(DictionaryAPI dictionary) {
		super();
		this.dictionary = dictionary;
	}

	public SMeritEntryView build(ModelToViewModelMapperAPI mapper, MeritAPI merit){
		SMeritEntryView view = new SMeritEntryView(dictionary, (MeritEntryViewAttibutesAPI) mapper.getViewAttributes(merit));
		view.setCost(merit.getCost());
		view.setText(merit.getName());
		return view;
	}

}
