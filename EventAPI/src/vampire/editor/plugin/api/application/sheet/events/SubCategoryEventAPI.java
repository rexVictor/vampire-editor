package vampire.editor.plugin.api.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.SubCategoryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;

public interface SubCategoryEventAPI {
	
	public SubCategoryControllerAPI getSource();
	
	public TraitControllerAPI getRemoved();
	
	public TraitControllerAPI getAdded();

}
