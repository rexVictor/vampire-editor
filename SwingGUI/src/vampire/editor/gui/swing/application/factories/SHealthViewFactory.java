package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.HorizontalHealthView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.HealthAPI;
import vampire.editor.plugin.api.domain.sheet.HealthEntryAPI;
import vampire.editor.plugin.api.domain.sheet.view.HealthViewAttributesAPI;
import vampire.editor.plugin.api.plugin.view.factories.AbstractViewsFactory;
import vampire.editor.plugin.api.plugin.view.factories.HealthViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.HealthViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;
import vampire.editor.plugin.api.view.sheet.HealthView;

public class SHealthViewFactory extends NonLeafAbstractFactory<HealthAPI, HealthEntryAPI, 
																HealthEntryView, HealthView, HealthViewFactoryModule>
									implements HealthViewFactory{
	
	public SHealthViewFactory(
			AbstractViewsFactory<HealthEntryAPI, HealthEntryView, ?> viewFactory,
			DictionaryAPI dictionary) {
		super(viewFactory, dictionary);
	}



	@Override
	protected HealthView constructView(Object viewAtts, HealthAPI m) {
		return new HorizontalHealthView(dictionary, (HealthViewAttributesAPI) viewAtts);
	}

}
