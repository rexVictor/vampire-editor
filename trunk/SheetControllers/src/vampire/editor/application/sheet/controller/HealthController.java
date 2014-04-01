/*******************************************************************************
 * Vampire Editor SheetControllers.
 * Copyright (C) 2014  Matthias Johannes Reimchen
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.application.sheet.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.application.sheet.events.HealthEvent;
import vampire.editor.plugin.api.application.sheet.controller.HealthControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.HealthEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.HealthListener;
import vampire.editor.plugin.api.domain.sheet.Health;
import vampire.editor.plugin.api.domain.sheet.HealthEntry;
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

	@Override
	public Health getHealth() {
		return health;
	}

	@Override
	public HealthView getView() {
		return view;
	}

	@Override
	public void addHealthEntry(HealthEntryControllerAPI controller) {
		lock.lock();
		try{
			view.add(controller.getView());
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
