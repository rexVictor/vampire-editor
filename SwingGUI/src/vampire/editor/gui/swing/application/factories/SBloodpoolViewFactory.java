package vampire.editor.gui.swing.application.factories;

import vampire.editor.gui.swing.view.SBloodPoolView;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.sheet.BloodPoolAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.view.BloodPoolViewAttributesAPI;
import vampire.editor.plugin.api.plugin.view.factories.BloodpoolViewFactory;
import vampire.editor.plugin.api.plugin.view.factories.BloodpoolViewFactoryModule;
import vampire.editor.plugin.api.view.sheet.BloodPoolView;

public class SBloodpoolViewFactory extends AbstractFactory<BloodPoolView, BloodPoolAPI, BloodpoolViewFactoryModule>
									implements BloodpoolViewFactory{
	
	
	public SBloodpoolViewFactory(DictionaryAPI dictionary) {
		super(dictionary);
	}

	@Override
	public BloodPoolView build(ModelToViewModelMapperAPI mapper, BloodPoolAPI m) {
		callModulesInitial(m, mapper);
		SBloodPoolView view = new SBloodPoolView((BloodPoolViewAttributesAPI) mapper.getViewAttributes(m), dictionary);
		view.setMaxValue(m.getMaxValue());
		view.setValue(m.getValue());
		callModulesFinal(m, mapper, view);
		return view;
	}

}
