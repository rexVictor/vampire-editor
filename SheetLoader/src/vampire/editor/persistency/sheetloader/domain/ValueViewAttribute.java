package vampire.editor.persistency.sheetloader.domain;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

public class ValueViewAttribute implements ValueViewAttributes{
	
	private boolean dynamic;
	
	private boolean showSpace;
	
	private int circleCount;
	
	

	public ValueViewAttribute(ProtoValueViewAttribute att) {
		super();
		this.dynamic = att.isDynamic();
		this.showSpace = att.isShowSpace();
		this.circleCount = att.getCircles();
	}

	@Override
	public boolean isDynamic() {
		return dynamic;
	}

	@Override
	public boolean showSpace() {
		return showSpace;
	}

	@Override
	public int circleCount() {
		return circleCount;
	}
	

}
