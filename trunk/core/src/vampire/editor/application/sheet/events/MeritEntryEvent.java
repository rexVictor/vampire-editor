package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.events.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryEventAPI;

public class MeritEntryEvent implements MeritEntryEventAPI{
	
	private final MeritEntryControllerAPI source;
	
	private final int formerCost;
	
	private final int newCost;
	
	private final String formerName;
	
	private final String newName;

	public MeritEntryEvent(MeritEntryControllerAPI source, int formerCost,
			int newCost, String formerName, String newName) {
		super();
		this.source = source;
		this.formerCost = formerCost;
		this.newCost = newCost;
		this.formerName = formerName;
		this.newName = newName;
	}

	@Override
	public MeritEntryControllerAPI getSource() {
		return source;
	}

	@Override
	public int getFormerCost() {
		return formerCost;
	}

	@Override
	public int getNewCost() {
		return newCost;
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
