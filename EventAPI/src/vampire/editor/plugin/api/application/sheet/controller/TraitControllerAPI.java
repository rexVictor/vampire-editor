package vampire.editor.plugin.api.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.events.TraitListener;
import vampire.editor.plugin.api.domain.sheet.TraitAPI;
import vampire.editor.plugin.api.view.sheet.TraitView;


public interface TraitControllerAPI {

	public void setTraitName(String name);

	public void addListener(TraitListener listener);

	public void removeListener(TraitListener listener);

	public TraitAPI getTrait();

	public TraitView getTraitView();
	
	public TraitControllerAPI clone();
	
	public ValueControllerAPI getValueController();
	
}