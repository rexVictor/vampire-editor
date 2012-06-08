package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.SubCategoryListener;
import vampire.editor.plugin.api.domain.sheet.SubCategoryAPI;
import vampire.editor.plugin.api.view.sheet.SubCategoryView;

public interface SubCategoryControllerAPI {

	public void addTrait(TraitControllerAPI traitController);

	public TraitControllerAPI addTrait();

	public void removeTrait(TraitControllerAPI traitController);

	public void insertTrait(int index, TraitControllerAPI controller);

	public void addListener(SubCategoryListener listener);

	public void removeListener(SubCategoryListener listener);

	public SubCategoryAPI getSubCategory();

	public SubCategoryView getView();

}