package vampire.editor.domain.sheet.view;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

public class ValueViewAttribute implements ValueViewAttributes{
	
	private boolean showSpace;
	
	private boolean dynamic;
	
	private int circles;

	public boolean isShowSpace() {
		return showSpace;
	}

	public void setShowSpace(boolean showSpace) {
		this.showSpace = showSpace;
	}

	public boolean isDynamic() {
		return dynamic;
	}

	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}

	public int getCircles() {
		return circles;
	}

	public void setCircles(int circles) {
		this.circles = circles;
	}
	
	

}
