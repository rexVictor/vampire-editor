package application.sheet.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import application.sheet.events.TraitEvent;

import plugin.api.application.sheet.controller.TraitControllerAPI;
import plugin.api.application.sheet.events.TraitListener;
import plugin.api.view.events.TraitViewEvent;
import plugin.api.view.events.TraitViewListener;
import plugin.api.view.sheet.TraitView;
import domain.sheet.Trait;

public class TraitController implements TraitViewListener, TraitControllerAPI{
	
	private final Trait trait;
	
	private final TraitView traitView;
	
	private final List<TraitListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock(true);
	
	

	public TraitController(Trait trait, TraitView traitView) {
		super();
		this.trait = trait;
		this.traitView = traitView;
	}



	@Override
	public void traitNameChanged(TraitViewEvent viewEvent) {
		setTraitName(viewEvent.getName());
	}
	
	@Override
	public void setTraitName(String name){
		TraitEvent event = new TraitEvent(this, trait.getName(), name);
		lock.lock();
		try{
			trait.setName(name);
			traitView.setName(name);
			for (TraitListener l : listeners){
				l.traitNameChanged(event);
			}
		}
		finally{
			lock.unlock();
		}
		
	}
	
	@Override
	public void addListener(TraitListener listener){
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}
	
	@Override
	public void removeListener(TraitListener listener){
		lock.lock();
		try{
			listeners.remove(listener);
		}
		finally{
			lock.unlock();
		}
	}



	@Override
	public Trait getTrait() {
		return trait;
	}



	@Override
	public TraitView getTraitView() {
		return traitView;
	}
	
	
	
	
	
	

}
