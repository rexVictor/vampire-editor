package vampire.editor.plugin.api.plugin.view.factories;

import vampire.editor.plugin.api.domain.sheet.BloodPoolAPI;
import vampire.editor.plugin.api.domain.sheet.HealthAPI;
import vampire.editor.plugin.api.domain.sheet.MeritsAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.view.sheet.MiscView;

public interface MiscViewFactory {
	
	public void add(MiscViewFactoryModule module);
	
	public MiscView build(ModelToViewModelMapperAPI mapper, BloodPoolAPI bloodPool, HealthAPI health, MeritsAPI merits,
						MeritsAPI flaws);
}
