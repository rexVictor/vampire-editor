package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.TraitMouseEventAPI;

public class TraitMouseEvent implements TraitMouseEventAPI{
	
	private final TraitControllerAPI source;
	
	private final int clickCount;
	
	private final int button;

	public TraitMouseEvent(TraitControllerAPI source, int clickCount, int button) {
		super();
		this.source = source;
		this.clickCount = clickCount;
		this.button = button;
	}
	
	@Override
	public TraitControllerAPI getSource() {
		return source;
	}

	@Override
	public int getClickCount() {
		return clickCount;
	}

	@Override
	public int getButton() {
		return button;
	}
	
	

}
