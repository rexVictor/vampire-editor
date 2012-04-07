package vampire.editor.persistency.sheetloader.domain;

public class ProtoValueViewAttribute {
	
	private final boolean showSpace;
	
	private final boolean dynamic;
	
	private final int circles;
	
	
	
	
	public ProtoValueViewAttribute(boolean showSpace, boolean dynamic,
			int circles) {
		super();
		this.showSpace = showSpace;
		this.dynamic = dynamic;
		this.circles = circles;
	}

	@Override
	public boolean equals(Object obj){
		if (obj instanceof ProtoValueViewAttribute){
			ProtoValueViewAttribute comp = (ProtoValueViewAttribute) obj;
			return showSpace == comp.showSpace && dynamic == comp.dynamic && circles == comp.circles;
		}
		return false;		
	}
	
	@Override
	public int hashCode(){
		int hash = ((Boolean) showSpace).hashCode() % 1230;
		hash += 10 * ((Boolean) dynamic).hashCode() % 1230;
		hash += 100 * circles;
		
		return hash;
	}

	public boolean isShowSpace() {
		return showSpace;
	}

	public boolean isDynamic() {
		return dynamic;
	}	

	public int getCircles() {
		return circles;
	}

	public int getId() {
		return hashCode();
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();		
	}
	
	

	

}
