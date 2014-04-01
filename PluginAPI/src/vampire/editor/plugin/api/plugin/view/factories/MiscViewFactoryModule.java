package vampire.editor.plugin.api.plugin.view.factories;

import vampire.editor.plugin.api.domain.sheet.BloodPoolAPI;
import vampire.editor.plugin.api.domain.sheet.HealthAPI;
import vampire.editor.plugin.api.domain.sheet.MeritsAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.view.sheet.MiscView;

public interface MiscViewFactoryModule {
	
	public void processInitial(BloodPoolAPI bloodPool, HealthAPI health, MeritsAPI merits,
								MeritsAPI flaws, ModelToViewModelMapperAPI mapper);
	
	public void processFinal(BloodPoolAPI bloodPool, HealthAPI health, MeritsAPI merits,
							MeritsAPI flaws, ModelToViewModelMapperAPI mapper, MiscView view);

}
