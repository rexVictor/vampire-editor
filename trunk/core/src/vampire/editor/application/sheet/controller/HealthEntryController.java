package vampire.editor.application.sheet.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.application.sheet.events.HealthEntryEvent;
import vampire.editor.domain.sheet.HealthEntry;
import vampire.editor.plugin.api.application.sheet.controller.HealthEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.HealthEntryListener;
import vampire.editor.plugin.api.domain.sheet.DamageType;
import vampire.editor.plugin.api.domain.sheet.HealthEntryAPI;
import vampire.editor.plugin.api.view.events.HealthEntryViewListener;
import vampire.editor.plugin.api.view.events.HealthViewEntryEvent;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;

public class HealthEntryController implements HealthEntryControllerAPI, HealthEntryViewListener{
	
	private final HealthEntry healthEntry;
	
	private final HealthEntryView view;
	
	private final Lock lock = new ReentrantLock(true);
	
	private final List<HealthEntryListener> listeners = new LinkedList<>();
	

	public HealthEntryController(HealthEntry healthEntry, HealthEntryView view) {
		super();
		this.healthEntry = healthEntry;
		this.view = view;
		view.addListener(this);
	}

	@Override
	public void setDamageType(DamageType damageType) {
		int formerPenalty = healthEntry.getPenalty();
		DamageType formerDamageType = healthEntry.getDamageType();
		String formerText = healthEntry.getName();
		HealthEntryEvent e = new HealthEntryEvent(this, formerPenalty, formerPenalty,
				formerDamageType, damageType, formerText, formerText);
		lock.lock();
		try{
			healthEntry.setDamageType(damageType);
			view.setDamageType(damageType);
			for (HealthEntryListener l : listeners){
				l.damageTypeChanged(e);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void setPenalty(int penalty) {
		int formerPenalty = healthEntry.getPenalty();
		DamageType formerDamageType = healthEntry.getDamageType();
		String formerText = healthEntry.getName();
		HealthEntryEvent e = new HealthEntryEvent(this, formerPenalty, penalty,
				formerDamageType, formerDamageType, formerText, formerText);
		lock.lock();
		try{
			healthEntry.setPenalty(penalty);
			view.setPenalty(penalty);
			for (HealthEntryListener l : listeners){
				l.penaltyChanged(e);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void setText(String text) {
		int formerPenalty = healthEntry.getPenalty();
		DamageType formerDamageType = healthEntry.getDamageType();
		String formerText = healthEntry.getName();
		HealthEntryEvent e = new HealthEntryEvent(this, formerPenalty, formerPenalty,
				formerDamageType, formerDamageType, formerText, text);
		lock.lock();
		try{
			healthEntry.setName(text);
			view.setDescription(text);
			for (HealthEntryListener l : listeners){
				l.textChanged(e);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void addListener(HealthEntryListener l) {
		lock.lock();
		try{
			listeners.add(l);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void removeListener(HealthEntryListener l) {
		lock.lock();
		try{
			listeners.remove(l);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public HealthEntryAPI getHealthEntry() {
		return healthEntry;
	}

	@Override
	public HealthEntryView getView() {
		return view;
	}

	@Override
	public void damageTypeChanged(HealthViewEntryEvent event) {
		setDamageType(event.getDamageType());
	}

	@Override
	public void penaltyChanged(HealthViewEntryEvent event) {
		setPenalty(event.getPenalty());
	}

	@Override
	public void descriptionChanged(HealthViewEntryEvent event) {
		setText(event.getDescription());
	}

}