package vampire.editor.plugin.freebiecalculator.application.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueEventAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueListener;
import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeEvent;
import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeListener;
import vampire.editor.plugin.freebiecalculator.domain.DependentTraitConfig;

public class FDependentController implements ValueListener{
	
	private class FreeAmountCalculator implements ValueListener{

		@Override
		public void valueChanged(ValueEventAPI event) {
			freeAmount -= event.getFormerValue();
			freeAmount += event.getNewValue();
			int formerFreebies = freebies;
			calculateFreebies();
			fireFreebieEvent(formerFreebies);
		}

		@Override
		public void tempValueChanged(ValueEventAPI event) {}
		
	}
	
	private final Lock lock = new ReentrantLock();
	
	private final List<FreebieChangeListener> listeners = new LinkedList<>();
	
	private DependentTraitConfig config;
	
	private int freeAmount = 0;
	
	private int freebies = 0;
	
	private int value = 0;
	
	public FDependentController(DependentTraitConfig config, ValueControllerAPI controller,
				ValueControllerAPI... dependents){
		this.config = config;
		controller.addListener(this);
		FreeAmountCalculator calculator = new FreeAmountCalculator();
		for (int i = 0; i < dependents.length; i++){
			dependents[i].addListener(calculator);
			freeAmount += dependents[i].getValue().getValue();
		}
		value = controller.getValue().getValue();
		calculateFreebies();
	}
	
	private void calculateFreebies(){
		freebies = value - freeAmount;
		if (freebies < 0){
			freebies = 0;
		}
		freebies *= config.getFactor();
	}
	
	private void fireFreebieEvent(int formerFreebies){
		lock.lock();
		try{
			FreebieChangeEvent e = new FreebieChangeEvent(formerFreebies, freebies);
			for (FreebieChangeListener l : listeners){
				l.freebieChanged(e);
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	public void addListener(FreebieChangeListener l){
		listeners.add(l);
	}

	@Override
	public void valueChanged(ValueEventAPI event) {
		value = event.getNewValue();
		int formerFreebies = freebies;
		calculateFreebies();
		fireFreebieEvent(formerFreebies);
	}
	
	public void setConfig(DependentTraitConfig config){
		this.config = config;
		calculateFreebies();
	}

	@Override
	public void tempValueChanged(ValueEventAPI event) {}

	public int getFreebies(){
		return freebies;
	}
}
