package vampire.editor.persistency.sheetloader.domain;


public class ProtoValue {
	
	private final int minValue;
	
	private final int maxValue;
	
	private final int value;
	
	private final int tempValue;
	
	private final int viewID;

	public ProtoValue(int minValue, int maxValue, int value, int tempValue,
			int viewID) {
		super();
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.value = value;
		this.tempValue = tempValue;
		this.viewID = viewID;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj instanceof ProtoValue){
			ProtoValue comp = (ProtoValue) obj;
			return comp.minValue == minValue && comp.maxValue == maxValue 
					&& comp.value == value && comp.tempValue == tempValue && comp.viewID == viewID;
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return minValue+10*maxValue+100*value+1000*tempValue+10000*viewID;		
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
