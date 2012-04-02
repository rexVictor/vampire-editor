package plugin.api.application.sheet.controller;

import plugin.api.application.sheet.events.TraitListener;
import plugin.api.domain.sheet.TraitAPI;
import plugin.api.view.sheet.TraitView;


public interface TraitControllerAPI {

	public void setTraitName(String name);

	public void addListener(TraitListener listener);

	public void removeListener(TraitListener listener);

	public TraitAPI getTrait();

	public TraitView getTraitView();

}