package vampire.editor.plugin.freebiecalculator.application.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueEventAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueListener;
import vampire.editor.plugin.freebiecalculator.application.events.SumChangeEvent;
import vampire.editor.plugin.freebiecalculator.application.events.SumChangeListener;

public class FSubCatController implements ValueListener, Comparable<FSubCatController>{
	
	private final Lock lock = new ReentrantLock();
	
	private int sum = 0;
	
	private List<SumChangeListener> listeners = new LinkedList<>();
	
	public FSubCatController(List<ValueControllerAPI> valueControllers){
		for (ValueControllerAPI c : valueControllers){
			c.addListener(this);
			sum += c.getValue().getValue();
		}
	}
	
	private void fireEvent(int formerSum){
		lock.lock();
		try{
			SumChangeEvent e = new SumChangeEvent(this, formerSum, sum);
			for (SumChangeListener l : listeners){
				l.sumChanged(e);
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	public void addSumChangeListener(SumChangeListener l){
		listeners.add(l);
	}
	
	public int getSum(){
		return sum;
	}

	@Override
	public void valueChanged(ValueEventAPI event) {
		int formerSum = sum;
		sum -= event.getFormerValue();
		sum += event.getNewValue();
		fireEvent(formerSum);
	}

	@Override
	public void tempValueChanged(ValueEventAPI event) {}

	@Override
	public int compareTo(FSubCatController o) {
		return Integer.compare(sum, o.sum);
	}

}
