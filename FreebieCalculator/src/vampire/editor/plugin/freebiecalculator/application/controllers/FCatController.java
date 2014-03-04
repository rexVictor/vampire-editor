package vampire.editor.plugin.freebiecalculator.application.controllers;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import java.util.concurrent.locks.Lock;

import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeEvent;
import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeListener;
import vampire.editor.plugin.freebiecalculator.application.events.SumChangeEvent;
import vampire.editor.plugin.freebiecalculator.application.events.SumChangeListener;
import vampire.editor.plugin.freebiecalculator.domain.FreebieCategoryConfig;

public class FCatController implements SumChangeListener{
	
	private final Lock lock = new ReentrantLock();
	
	private final List<FreebieChangeListener> listeners = new LinkedList<>();
	
	private final List<FSubCatController> subCatControllers;
	
	private FreebieCategoryConfig config;
	
	private int freebie = 0;
	
	public FCatController(List<FSubCatController> subCatControllers,
			FreebieCategoryConfig config) {
		super();
		this.subCatControllers = subCatControllers;
		this.config = config;
		for (FSubCatController c : subCatControllers){
			c.addSumChangeListener(this);
		}
		calculateFreebies();
	}
	
	private void calculateFreebies(){
		Collections.sort(subCatControllers);
		int sum = 0;
		for (int i = 0; i < subCatControllers.size(); i++){
			sum += subCatControllers.get(i).getSum();
			sum -= config.getFreeAmount(i);
			if (sum < 0){
				sum = 0;
			}
		}
		freebie = sum * config.getFactor();
	}
	
	private void fireFreebieEvent(int formerFreebies){
		lock.lock();
		try{
			FreebieChangeEvent e = new FreebieChangeEvent(formerFreebies, freebie);
			for (FreebieChangeListener l : listeners){
				l.freebieChanged(e);
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	public void add(FreebieChangeListener l){
		listeners.add(l);
	}

	@Override
	public void sumChanged(SumChangeEvent e) {
		int formerFreebies = freebie;
		calculateFreebies();
		fireFreebieEvent(formerFreebies);
	}
	
	public int getFreebies(){
		return freebie;
	}
	
	public void setConfig(FreebieCategoryConfig config){
		this.config = config;
		calculateFreebies();
	}

}
