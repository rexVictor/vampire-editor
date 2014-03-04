package vampire.editor.plugin.freebiecalculator.application.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.SubCategoryListener;
import vampire.editor.plugin.api.application.sheet.events.ValueEventAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueListener;
import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeEvent;
import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeListener;
import vampire.editor.plugin.freebiecalculator.domain.FreebieSubCategoryConfig;

public class FSingleSubCatController implements ValueListener, SubCategoryListener{
	
	private final Lock lock = new ReentrantLock();
	
	private final List<FreebieChangeListener> listeners = new LinkedList<>();
	
	private FreebieSubCategoryConfig config;
	
	private int freebie = 0;
	
	private int sum = 0;
	
	public FSingleSubCatController(List<ValueControllerAPI> valueControllers, FreebieSubCategoryConfig config){
		this.config = config;
		for (ValueControllerAPI c : valueControllers){
			c.addListener(this);
			sum += c.getValue().getValue();
		}
		calculateFreebies();
	}
	
	private void calculateFreebies(){
		int sum = this.sum;
		sum -= config.getFreeAmount();
		if (sum < 0){
			freebie = 0;
		}
		else{
			freebie = sum * config.getFactor();
		}
	}

	@Override
	public void valueChanged(ValueEventAPI event) {
		sum -= event.getFormerValue();
		sum += event.getNewValue();
		int formerFreebies = freebie;
		calculateFreebies();
		fireEvent(formerFreebies);
	}
	
	private void fireEvent(int formerFreebies){
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
	
	public int getFreebies(){
		return freebie;
	}
	
	public void setConfig(FreebieSubCategoryConfig config){
		this.config = config;
		calculateFreebies();
	}
	
	public void addListener(FreebieChangeListener l){
		listeners.add(l);
	}

	@Override
	public void tempValueChanged(ValueEventAPI event) {}

	@Override
	public void traitAdded(SubCategoryEventAPI event) {
		event.getAdded().getValueController().addListener(this);
	}

	@Override
	public void traitRemoved(SubCategoryEventAPI event) {
		event.getRemoved().getValueController().removeListener(this);
	}

}
