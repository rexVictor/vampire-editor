package vampire.editor.sheetloader.domain;


public class ProtoValue {
	
	private final int minValue;
	
	private final int maxValue;
	
	private final int value;
	
	private final int tempValue;
	
	private final int viewID;
	
	private final long hashCode;

	public ProtoValue(int minValue, int maxValue, int value, int tempValue,
			int viewID) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.value = value;
		this.tempValue = tempValue;
		this.viewID = viewID;
		this.hashCode = hash();
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj instanceof ProtoValue){
			if (hashCode != obj.hashCode()) return false;
			ProtoValue comp = (ProtoValue) obj;
			return comp.minValue == minValue && comp.maxValue == maxValue 
					&& comp.value == value && comp.tempValue == tempValue && comp.viewID == viewID;
		}
		return false;
	}
	
	private long hash(){
		return (long) minValue+100* (long) maxValue+10_000*(long) value+10_000_000*
				(long) tempValue+1_000_000_000* (long) viewID;		
	}
	
	@Override
	public int hashCode(){
		return (int) hashCode;
	}
	
	public long getId(){
		return hashCode;
	}

	public int getMinValue() {
		return minValue;
	}

	public int getMaxValue() {
		return maxValue;
	}

	public int getValue() {
		return value;
	}

	public int getTempValue() {
		return tempValue;
	}

	public int getViewID() {
		return viewID;
	}
	
	
	
	

	
	
}
