package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.SMeritView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.MeritAPI;
import vampire.editor.plugin.api.domain.sheet.MeritsAPI;
import vampire.editor.plugin.api.domain.sheet.view.MeritViewAttributesAPI;
import vampire.editor.plugin.api.plugin.MeritEntryViewFactory;
import vampire.editor.plugin.api.plugin.MeritViewFactory;
import vampire.editor.plugin.api.plugin.MeritViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;
import vampire.editor.plugin.api.view.sheet.MeritView;

public class SMeritViewFactory extends NonLeafAbstractFactory<MeritsAPI, MeritAPI,
															MeritEntryView, MeritView, MeritViewFactoryModule>
								implements MeritViewFactory{
	
	private final DictionaryAPI dictionary;
	
	public SMeritViewFactory(MeritEntryViewFactory meritEntryViewFactory, DictionaryAPI dictionary){
		super(meritEntryViewFactory);
		this.dictionary = dictionary;
	}
	
	@Override
	protected MeritView constructView(Object viewAtts, MeritsAPI m) {
		return new SMeritView(m.getName(), dictionary, (MeritViewAttributesAPI) viewAtts);
	}

}
