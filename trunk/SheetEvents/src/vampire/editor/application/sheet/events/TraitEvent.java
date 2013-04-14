package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.TraitEventAPI;

public class TraitEvent implements TraitEventAPI{
	
	private final TraitControllerAPI source;
	
	private final String formerName;
	
	private final String newName;

	public TraitEvent(TraitControllerAPI controller, String formerName, String newName) {
		super();
		this.source = controller;
		this.formerName = formerName;
		this.newName = newName;
	}
	
	@Override	
	public TraitControllerAPI getSource(){
		return source;
	}

	@Override	
	public String getFormerName() {
		return formerName;
	}
	
	@Override
	public String getNewName() {
		return newName;
	}
	
	
	
	

}
