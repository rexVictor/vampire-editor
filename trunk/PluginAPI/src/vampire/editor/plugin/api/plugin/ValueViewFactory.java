package vampire.editor.plugin.api.plugin;

import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.ValueAPI;
import vampire.editor.plugin.api.view.sheet.ValueView;

public interface ValueViewFactory {
	
	public ValueView build(ValueAPI value, ModelToViewModelMapperAPI mapper);

}
