package vampire.editor.plugin.freebiecalculator.application.controllers;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.plugin.api.application.sheet.events.MeritEntryControllerAPI;

import vampire.editor.plugin.api.application.sheet.events.MeritEntryEventAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryListener;
import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeEvent;
import vampire.editor.plugin.freebiecalculator.application.events.FreebieChangeListener;

public class FMeritController implements MeritEntryListener{
	
	private final Lock lock = new ReentrantLock();
	
	private final List<FreebieChangeListener> listeners = new LinkedList<>();
	
	private int freebies = 0;
	
	public FMeritController(List<MeritEntryControllerAPI> controllers){
		for (MeritEntryControllerAPI c : controllers){
			c.addListener(this);
		}
	}
	
	private void fireEvent(int formerFreebies){
		lock.lock();
		try{
			FreebieChangeEvent e = new FreebieChangeEvent(formerFreebies, freebies);
			for(FreebieChangeListener l : listeners){
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
	public void costChanged(MeritEntryEventAPI e) {
		int formerFreebies = freebies;
		freebies -= e.getFormerCost();
		freebies += e.getNewCost();
		fireEvent(formerFreebies);
	}

	@Override
	public void nameChanged(MeritEntryEventAPI e) {}

}
