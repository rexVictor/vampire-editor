package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.HorizontalHealthEntryView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.HealthEntryAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.view.HealthEntryViewAttributesAPI;
import vampire.editor.plugin.api.plugin.view.factories.HealthEntryViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.HealthEntryViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;

public class SHealthEntryViewFactory extends AbstractFactory<HealthEntryView, HealthEntryAPI, HealthEntryViewFactoryModule>
										implements HealthEntryViewFactory{
	
	public SHealthEntryViewFactory(DictionaryAPI dictionary) {
		super(dictionary);
	}

	@Override
	public HealthEntryView build(ModelToViewModelMapperAPI mapper,
			HealthEntryAPI m) {
		callModulesInitial(m, mapper);
		HorizontalHealthEntryView view = new HorizontalHealthEntryView(dictionary, (HealthEntryViewAttributesAPI) mapper.getViewAttributes(m));
		view.setDamageType(m.getDamageType());
		view.setDescription(m.getName());
		view.setPenalty(m.getPenalty());
		callModulesFinal(m, mapper, view);
		return view;
	}

}
