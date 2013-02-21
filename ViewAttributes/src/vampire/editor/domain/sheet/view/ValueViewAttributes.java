package vampire.editor.domain.sheet.view;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;


public class ValueViewAttributes implements ValueViewAttributesAPI{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1886427499077840867L;

	private boolean showSpace;
	
	private boolean dynamic;
	
	private int circles;
	
	private boolean tempSquared = false;
	
	private int size;
	
	public ValueViewAttributes(){
	}
	
	
	

	public ValueViewAttributes(boolean showSpace, boolean dynamic, int circles,
			boolean tempSquared, int size) {
		super();
		this.showSpace = showSpace;
		this.dynamic = dynamic;
		this.circles = circles;
		this.tempSquared = tempSquared;
		this.size = size;
	}




	@Override
	public boolean isShowSpace() {
		return showSpace;
	}

	public void setShowSpace(boolean showSpace) {
		this.showSpace = showSpace;
	}

	@Override
	public boolean isDynamic() {
		return dynamic;
	}

	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}

	@Override
	public int getCircles() {
		return circles;
	}

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
		builder.append(", size: ");
		builder.append(size);
		builder.append("\n");
		return builder.toString();
	}

	@Override
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	@Override
	public ValueViewAttributes clone(){
		ValueViewAttributes clone = new ValueViewAttributes();
		clone.circles = circles;
		clone.dynamic = dynamic;
		clone.showSpace = showSpace;
		clone.size = size;
		clone.tempSquared = tempSquared;
		return clone;
	}
	
	@Override
	public boolean equals(Object object){
		if (object instanceof ValueViewAttributes){
			ValueViewAttributes toCompare = (ValueViewAttributes) object;
			return toCompare.dynamic == dynamic && toCompare.showSpace == showSpace 
					&& toCompare.circles == circles	&& toCompare.size == size;
		}
		return false;
	}
	
	
	@Override
	public int hashCode(){
		return System.identityHashCode(dynamic)+2*System.identityHashCode(showSpace)+4*circles+8*size;
		
	}
	
	
	

}
