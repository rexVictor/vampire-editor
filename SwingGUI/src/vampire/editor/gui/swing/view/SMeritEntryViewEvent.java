package vampire.editor.gui.swing.view;

import vampire.editor.plugin.api.view.events.MeritEntryViewEvent;

public class SMeritEntryViewEvent implements MeritEntryViewEvent{
	
	private final int cost;
	
	private final String name;

	public SMeritEntryViewEvent(int cost, String name) {
		super();
		this.cost = cost;
		this.name = name;
	}

	public int getCost() {
		return cost;
	}

	public String getName() {
		return name;
	}
	
	

}
