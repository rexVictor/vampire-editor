package vampire.editor.plugin.freebiecalculator.domain;

import java.util.LinkedList;
import java.util.List;

import vampire.editor.plugin.freebiecalculator.application.events.FreebieCategoryAttributesChangeListener;

public class FreebieCategoryConfig {
	
	private int factor;
	
	private List<Integer> freeAmount = new LinkedList<>();
	
	private final List<FreebieCategoryAttributesChangeListener> listeners = new LinkedList<>();

	public int getFactor() {
		return factor;
	}

	public void setFactor(int freebieFactor) {
		this.factor = freebieFactor;
		fireChangeEvent();
	}
	
	public int getFreeAmount(int i){
		return freeAmount.get(i);
	}
	
	public void addChangeListener(FreebieCategoryAttributesChangeListener l){
		listeners.add(l);
	}
	
	public void setFreeAmount(int pos, int amount){
		freeAmount.set(pos, amount);
		fireChangeEvent();
	}
	
	public void setFreeAmount(List<Integer> freeAmounts){
		this.freeAmount = freeAmounts;
		fireChangeEvent();
	}
	
	private void fireChangeEvent(){
		for (FreebieCategoryAttributesChangeListener l : listeners){
			l.attributesChanged();
		}
	}
	
	

}
