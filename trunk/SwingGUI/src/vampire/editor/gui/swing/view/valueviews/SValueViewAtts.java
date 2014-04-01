package vampire.editor.gui.swing.view.valueviews;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;

public class SValueViewAtts{
	
	private boolean showSpace = true;
	
	private boolean dynamic = true;
	
	private boolean tempSquared = true;
	
	public SValueViewAtts(boolean showSpace, boolean dynamic,
			boolean tempSquared) {
		super();
		this.showSpace = showSpace;
		this.dynamic = dynamic;
		this.tempSquared = tempSquared;
	}

	public SValueViewAtts(ValueViewAttributesAPI viewAtts){
		showSpace = viewAtts.isShowSpace();
		dynamic = viewAtts.isDynamic();
		tempSquared = viewAtts.isTempSquared();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (dynamic ? 1231 : 1237);
		result = prime * result + (showSpace ? 1231 : 1237);
		result = prime * result + (tempSquared ? 1231 : 1237);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof SValueViewAtts))
			return false;
		SValueViewAtts other = (SValueViewAtts) obj;
		if (dynamic != other.dynamic)
			return false;
		if (showSpace != other.showSpace)
			return false;
		if (tempSquared != other.tempSquared)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SValueViewAtts [showSpace=" + showSpace + ", dynamic=" //$NON-NLS-1$ //$NON-NLS-2$
				+ dynamic + ", tempSquared=" + tempSquared + "]"; //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	

}
