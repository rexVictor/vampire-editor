package vampire.editor.plugin.sheetsummary.domain;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.api.domain.sheet.ValueAPI;

public class PseudoSubCat {
	
	private final List<ValueAPI> values = new LinkedList<>();
	
	public void addValue(ValueAPI value){
		values.add(value);
	}
	
	public void removeValue(ValueAPI value){
		values.remove(value);
	}
	
	public int sum(){
		int sum = 0;
		for (ValueAPI value : values){
			sum += value.getValue();
		}
		return sum;
	}

}
