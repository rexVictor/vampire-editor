package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.CategoriesListener;
import vampire.editor.plugin.api.domain.sheet.CategoriesAPI;
import vampire.editor.plugin.api.view.sheet.CategoriesView;

public interface CategoriesControllerAPI extends AbstractNonLeafControllerAPI<CategoriesAPI, CategoriesView,
													CategoriesListener, CategoryControllerAPI>{
	
}
