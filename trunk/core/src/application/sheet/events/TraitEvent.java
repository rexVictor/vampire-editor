package application.sheet.events;

import plugin.api.application.sheet.events.TraitEventAPI;

public class TraitEvent implements TraitEventAPI{
	
	private final String formerName;
	
	private final String newName;

	public TraitEvent(String formerName, String newName) {
		super();
		this.formerName = formerName;
		this.newName = newName;
	}

	public String getFormerName() {
		return formerName;
	}

	public String getNewName() {
		return newName;
	}
	
	
	
	

}
