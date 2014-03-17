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

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.application.sheet.events.TraitEvent;
import vampire.editor.application.sheet.events.TraitMouseEvent;
import vampire.editor.plugin.api.application.sheet.controller.TraitControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.TraitListener;
import vampire.editor.plugin.api.application.sheet.events.TraitMouseListener;
import vampire.editor.plugin.api.domain.sheet.Trait;
import vampire.editor.plugin.api.view.events.TraitMouseViewEvent;
import vampire.editor.plugin.api.view.events.TraitMouseViewListener;
import vampire.editor.plugin.api.view.events.TraitViewEvent;
import vampire.editor.plugin.api.view.events.TraitViewListener;
import vampire.editor.plugin.api.view.sheet.TraitView;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class TraitController implements TraitViewListener, TraitControllerAPI, TraitMouseViewListener{
	
	private final Trait trait;
	
	private final TraitView traitView;
	
	private final List<TraitListener> listeners = new LinkedList<>();
	
	private final List<TraitMouseListener> mouseListeners = new LinkedList<>();
	
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
		ValueView cloneValueView = cloneView.getValueView();
		ValueController cloneValueController = new ValueController(cloneTrait.getValue(), cloneValueView);
		return new TraitController(cloneValueController, cloneTrait, cloneView);
	}



	@Override
	public void addMouseListener(TraitMouseListener listener) {
		mouseListeners.add(listener);
	}



	@Override
	public void mouseViewEventFired(TraitMouseViewEvent e) {
		TraitMouseEvent event = new TraitMouseEvent(this, e.getClickCount(), e.getButton());
		for (TraitMouseListener l : mouseListeners){
			l.mouseEventFired(event);
		}
	}
	
	
	
	
	
	

}
