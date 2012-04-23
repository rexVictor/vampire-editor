package vampire.editor.sheetloader.domain;

import java.lang.reflect.Field;

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
		int hash = 0;
		if (showSpace) {
			hash += 1;
		}
		if (dynamic) {
			hash += 2*1;
		}
		hash += 4*circles;
		
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
	public String toString(){
		Field[] fields = this.getClass().getDeclaredFields();
		String toReturn = "";
		for (Field f : fields){
			try {
				toReturn = toReturn + f.getName()+" : "+f.get(this)+"\n";
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return toReturn;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();		
	}
	
	

	

}
