package vampire.editor.plugin.dynamicmeritentrymanager.application;

import vampire.editor.plugin.api.application.sheet.controller.MeritsControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryListener;
import vampire.editor.plugin.api.application.sheet.events.MeritsEventAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritsListener;
import vampire.editor.plugin.api.domain.sheet.MeritAPI;
import vampire.editor.plugin.api.domain.sheet.ModelToViewModelMapperAPI;
import vampire.editor.plugin.api.domain.sheet.view.MeritEntryViewAttibutesAPI;

public class MeritAdder implements MeritEntryListener, MeritsListener{
	
	private final MeritsControllerAPI meritsController;
	
	private final ModelToViewModelMapperAPI mapper;

	public MeritAdder(MeritsControllerAPI meritsController, ModelToViewModelMapperAPI mapper) {
		super();
		this.meritsController = meritsController;
		this.mapper = mapper;
		meritsController.addListener(this);
		for (MeritEntryControllerAPI controller : meritsController){
			controller.addListener(this);
		}
	}

	@Override
	public void meritAdded(MeritsEventAPI e) {
		e.getAdded().addListener(this);
		
	}

	@Override
	public void meritRemoved(MeritsEventAPI e) {
		e.getRemoved().removeListener(this);
	}

	@Override
	public void costChanged(MeritEntryEventAPI e) {}

	@Override
	public void nameChanged(MeritEntryEventAPI e) {
		MeritEntryControllerAPI meritController = e.getSource();
		int lastIndex = meritsController.size()-1;
		MeritEntryControllerAPI first = meritsController.getController(0);
		int positionOccured = meritsController.indexOf(meritController);
		if (positionOccured == lastIndex){
			if (!e.getNewName().trim().isEmpty()){
					MeritEntryControllerAPI clone = first.clone();
					clone.setName("");
					clone.setCost(0);
					MeritAPI trait = clone.getMerit();
					MeritEntryViewAttibutesAPI traitViewAtts = clone.getView().getViewAttributes();
					mapper.putView(trait, traitViewAtts);
					meritsController.addMerit(clone);
			}
			else if (lastIndex != 0){
					meritsController.removeMerit(meritController);
					MeritAPI merit = meritController.getMerit();
					mapper.removeView(merit);
			}
		}
		
	}
	

}
