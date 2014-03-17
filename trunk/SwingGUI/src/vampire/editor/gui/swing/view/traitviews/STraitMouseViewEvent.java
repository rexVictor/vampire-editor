package vampire.editor.gui.swing.view.traitviews;

import vampire.editor.plugin.api.view.events.TraitMouseViewEvent;

public class STraitMouseViewEvent implements TraitMouseViewEvent{
	
	private final int clickCount;
	
	private final int button;

	public STraitMouseViewEvent(int clickCount, int button) {
		super();
		this.clickCount = clickCount;
		this.button = button;
	}

	public int getClickCount() {
		return clickCount;
	}

	public int getButton() {
		return button;
	}
	
	


}
