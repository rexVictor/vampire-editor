package vampire.editor.domain.sheet.view;

import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;

public class ValueViewAttributes implements IValueViewAttributes{
	
	private boolean showSpace;
	
	private boolean dynamic;
	
	private int circles;
	
	private boolean tempSquared = false;

	@Override
	public boolean isShowSpace() {
		return showSpace;
	}

	@Override
	public void setShowSpace(boolean showSpace) {
		this.showSpace = showSpace;
	}

	@Override
	public boolean isDynamic() {
		return dynamic;
	}

	@Override
	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}

	@Override
	public int getCircles() {
		return circles;
	}

	@Override
	public void setCircles(int circles) {
		this.circles = circles;
	}
	
	
	@Override
	public boolean isTempSquared() {
		return tempSquared;
	}

	public void setTempSquared(boolean tempSquared) {
		this.tempSquared = tempSquared;
	}

	@Override
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("dynamic: ");
		builder.append(dynamic);
		builder.append(", showspace: ");
		builder.append(showSpace);
		builder.append(", circles: ");
		builder.append(circles);
		builder.append("\n");
		return builder.toString();
	}
	
	

}
