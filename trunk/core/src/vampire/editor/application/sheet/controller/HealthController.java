package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.application.sheet.events.HealthEvent;
import vampire.editor.domain.sheet.Health;
import vampire.editor.domain.sheet.HealthEntry;
import vampire.editor.plugin.api.application.sheet.controller.HealthControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.HealthEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.HealthListener;
import vampire.editor.plugin.api.view.events.HealthViewListener;
import vampire.editor.plugin.api.view.sheet.HealthView;

public class HealthController implements HealthControllerAPI, HealthViewListener{
	
	private final Health health;
	
	private final HealthView view;
	
	private final Lock lock = new ReentrantLock();
	
	private final List<HealthListener> listeners = new LinkedList<>();
	
	private final List<HealthEntryControllerAPI> entryControllers = new ArrayList<>();

	public HealthController(Health health, HealthView view) {
		super();
		this.health = health;
		this.view = view;
		view.addListener(this);
	}

	public Health getHealth() {
		return health;
	}

	public HealthView getView() {
		return view;
	}

	@Override
	public void addHealthEntry(HealthEntryControllerAPI controller) {
		lock.lock();
		try{
			view.addHealthEntryView(controller.getView());
			health.add((HealthEntry) controller.getHealthEntry());
			entryControllers.add(controller);
			HealthEvent event = new HealthEvent(this, controller, null);
			for (HealthListener l : listeners){
				l.healthEntryAdded(event);
			}
		}
		finally{
			lock.unlock();
		}
		
	}
	
	void addHealthEntry0(HealthEntryControllerAPI controller) {
		lock.lock();
		try{
			entryControllers.add(controller);
			HealthEvent event = new HealthEvent(this, controller, null);
			for (HealthListener l : listeners){
				l.healthEntryAdded(event);
			}
		}
		finally{
			lock.unlock();
		}
		
	}

	@Override
	public void removeHealthEntry(HealthEntryControllerAPI controller) {
		lock.lock();
		try{
			view.removeHealthEntryView(controller.getView());
			health.remove((HealthEntry) controller.getHealthEntry());
			entryControllers.remove(controller);
			HealthEvent event = new HealthEvent(this, null, controller);
			for (HealthListener l : listeners){
				l.healthEntryRemoved(event);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void addListener(HealthListener l) {
		lock.lock();
		try{
			listeners.add(l);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void removeListener(HealthListener l) {
		lock.lock();
		try{
			listeners.remove(l);
		}
		finally{
			lock.unlock();
		}
	}
	
	

}
