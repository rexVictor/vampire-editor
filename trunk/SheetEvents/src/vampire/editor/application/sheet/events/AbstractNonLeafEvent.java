package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.events.NonLeafEvent;

public class AbstractNonLeafEvent<C, SC> implements NonLeafEvent<C, SC>{
	
	private final C source;
	
	private final SC reason;
	
	private final int index;

	public AbstractNonLeafEvent(C source, SC reason, int index) {
		super();
		this.source = source;
		this.reason = reason;
		this.index = index;
	}

	@Override
	public C getSource() {
		return source;
	}

	@Override
	public SC getReason() {
		return reason;
	}

	@Override
	public int getIndex() {
		return index;
	}
	
	

}
