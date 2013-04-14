package vampire.editor.application.sheet.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import vampire.editor.application.sheet.events.TraitEvent;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.TraitListener;
import vampire.editor.plugin.api.domain.sheet.Trait;
import vampire.editor.plugin.api.view.events.TraitViewEvent;
import vampire.editor.plugin.api.view.events.TraitViewListener;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class TraitController implements TraitViewListener, TraitControllerAPI{
	
	private final Trait trait;
	
	private final TraitView traitView;
	
	private final List<TraitListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock(true);
	
	private final ValueController valueController;
	
	public TraitController(ValueController valueController, Trait trait, TraitView traitView) {
		super();
		this.trait = trait;
		this.traitView = traitView;
		this.valueController = valueController;
		traitView.addListener(this);
	}



	@Override
	public void traitNameChanged(TraitViewEvent viewEvent) {
		String name = viewEvent.getName();
		final TraitEvent event = new TraitEvent(this, trait.getName(), name);
		lock.lock();
		try{
			trait.setName(name);
			for (TraitListener l : listeners){
				final TraitListener listener = l;
				new Thread(){
					@Override
					public void run(){
						listener.traitNameChanged(event);
					}
				}.start();
				
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	@Override
	public void setTraitName(String name){
		final TraitEvent event = new TraitEvent(this, trait.getName(), name);
		lock.lock();
		try{
			trait.setName(name);
			traitView.setName(name);
			for (TraitListener l : listeners){
				final TraitListener listener = l;
				new Thread(){
					@Override
					public void run(){
						listener.traitNameChanged(event);
					}
				}.start();
				
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
	
	public ValueController getValueController(){
		return valueController;
	}
	
	public TraitController clone(){
		Trait cloneTrait = trait.clone();
		TraitView cloneView = traitView.clone();
		ValueView cloneValueView = traitView.getValueView();
		ValueController cloneValueController = new ValueController(cloneTrait.getValue(), cloneValueView);
		return new TraitController(cloneValueController, cloneTrait, cloneView);
	}
	
	
	
	
	
	

}
