package vampire.editor.application.sheet.events;

import vampire.editor.application.sheet.controller.TraitController;
import vampire.editor.plugin.api.application.sheet.events.TraitEventAPI;

public class TraitEvent implements TraitEventAPI{
	
	private final TraitController source;
	
	private final String formerName;
	
	private final String newName;

	public TraitEvent(TraitController controller, String formerName, String newName) {
		super();
		this.source = controller;
		this.formerName = formerName;
		this.newName = newName;
	}
	
	@Override	
	public TraitController getSource(){
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
