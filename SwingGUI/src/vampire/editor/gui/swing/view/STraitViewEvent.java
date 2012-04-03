package vampire.editor.gui.swing.view;

import vampire.editor.plugin.api.view.events.TraitViewEvent;

public class STraitViewEvent implements TraitViewEvent{
	
	private final String name;
	
	

	public STraitViewEvent(String name) {
		super();
		this.name = name;
	}



	@Override
	public String getName() {
		return name;
	}

}
