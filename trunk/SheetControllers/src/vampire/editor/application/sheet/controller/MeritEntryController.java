package vampire.editor.application.sheet.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.application.sheet.events.MeritEntryEvent;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryListener;
import vampire.editor.plugin.api.domain.sheet.Merit;
import vampire.editor.plugin.api.domain.sheet.MeritAPI;
import vampire.editor.plugin.api.view.events.MeritEntryViewEvent;
import vampire.editor.plugin.api.view.events.MeritEntryViewListener;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;

public class MeritEntryController implements MeritEntryControllerAPI, MeritEntryViewListener{
	
	private final Merit merit;
	
	private final MeritEntryView view;
	
	private final Lock lock = new ReentrantLock(true);
	
	private final List<MeritEntryListener> listeners = new LinkedList<>();

	public MeritEntryController(Merit merit, MeritEntryView view) {
		super();
		this.merit = merit;
		this.view = view;
		view.addListener(this);
	}

	@Override
	public MeritAPI getMerit() {
		return merit;
	}

	@Override
	public MeritEntryView getView() {
		return view;
	}

	@Override
	public void addListener(MeritEntryListener l) {
		lock.lock();
		try{
			listeners.add(l);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void removeListener(MeritEntryListener l) {
		lock.lock();
		try{
			listeners.remove(l);
		}
		finally{
			lock.unlock();
		}
		
	}

	@Override
	public void setCost(int cost) {
		final MeritEntryEvent e = new MeritEntryEvent(this, merit.getCost(), cost, merit.getName(), merit.getName());
		lock.lock();
		try{
			merit.setCost(cost);
			view.setCost(cost);
			for (MeritEntryListener l : listeners){
				final MeritEntryListener listener = l;
				new Thread(){
					@Override
					public void run(){
						listener.costChanged(e);
					}
				}.start();
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void setName(String name) {
		final MeritEntryEvent e = new MeritEntryEvent(this, merit.getCost(), merit.getCost(), merit.getName(), name);
		lock.lock();
		try{
			merit.setName(name);
			view.setText(name);
			for (MeritEntryListener l : listeners){
				final MeritEntryListener listener = l;
				new Thread(){
					@Override
					public void run(){
						listener.nameChanged(e);
					}
				}.start();
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void costChanged(MeritEntryViewEvent event) {
		int cost = event.getCost();
		final MeritEntryEvent e = new MeritEntryEvent(this, merit.getCost(), cost, merit.getName(), merit.getName());
		lock.lock();
		try{
			merit.setCost(cost);
			for (MeritEntryListener l : listeners){
				final MeritEntryListener listener = l;
				new Thread(){
					@Override
					public void run(){
						listener.costChanged(e);
					}
				}.start();
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void nameChanged(MeritEntryViewEvent event) {
		String name = event.getName();
		final MeritEntryEvent e = new MeritEntryEvent(this, merit.getCost(), merit.getCost(), merit.getName(), name);
		lock.lock();
		try{
			merit.setName(name);
			for (MeritEntryListener l : listeners){
				final MeritEntryListener listener = l;
				new Thread(){
					@Override
					public void run(){
						listener.nameChanged(e);
					}
				}.start();
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	@Override
	public MeritEntryController clone(){
		Merit cloneMerit = merit.clone();
		MeritEntryView cloneEntryView = view.clone();
		MeritEntryController clone = new MeritEntryController(cloneMerit, cloneEntryView);
		return clone;
	}
	
	
}