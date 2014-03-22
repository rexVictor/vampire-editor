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

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import vampire.editor.application.sheet.events.MeritsEvent;
import vampire.editor.plugin.api.application.sheet.controller.MeritsControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritsListener;
import vampire.editor.plugin.api.domain.sheet.Merit;
import vampire.editor.plugin.api.domain.sheet.Merits;
import vampire.editor.plugin.api.domain.sheet.MeritsAPI;
import vampire.editor.plugin.api.view.sheet.MeritView;

public class MeritsController implements MeritsControllerAPI{
	
	private final Merits merits;
	
	private final MeritView view;
	
	private final Lock lock = new ReentrantLock(true);
	
	private final List<MeritsListener> listeners = new LinkedList<>();
	
	private final List<MeritEntryControllerAPI> entryControllers = new LinkedList<>();

	public MeritsController(Merits merits, MeritView view) {
		super();
		this.merits = merits;
		this.view = view;
	}

	@Override
	public MeritsAPI getMerits() {
		return merits;
	}

	@Override
	public MeritView getView() {
		return view;
	}

	@Override
	public void addMerit(MeritEntryControllerAPI merit) {
		final MeritsEvent e = new MeritsEvent(this, merit, null);
		lock.lock();
		try{
			merits.add((Merit) merit.getMerit());
			view.add(merit.getView());
			entryControllers.add(merit);
			for (MeritsListener l : listeners){
				final MeritsListener listener = l;
				new Thread(){
					@Override
					public void run(){
						listener.meritAdded(e);
					}
				}.start();
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	void addMerit0(MeritEntryControllerAPI merit){
		final MeritsEvent e = new MeritsEvent(this, merit, null);
		lock.lock();
		try{
			entryControllers.add(merit);
			for (MeritsListener l : listeners){
				final MeritsListener listener = l;
				new Thread(){
					@Override
					public void run(){
						listener.meritAdded(e);
					}
				}.start();
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void removeMerit(MeritEntryControllerAPI merit) {
		final MeritsEvent e = new MeritsEvent(this, null, merit);
		lock.lock();
		try{
			merits.remove((Merit) merit.getMerit());
			view.removeMeritEntryView(merit.getView());
			entryControllers.remove(merit);
			for (MeritsListener l : listeners){
				final MeritsListener listener = l;
				new Thread(){
					@Override
					public void run(){
						listener.meritRemoved(e);
					}
				}.start();
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void addListener(MeritsListener l) {
		lock.lock();
		try{
			listeners.add(l);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void removeListener(MeritsListener l) {
		lock.lock();
		try{
			listeners.remove(l);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public int size() {
		return entryControllers.size();
	}

	@Override
	public int indexOf(MeritEntryControllerAPI controller) {
		return entryControllers.indexOf(controller);
	}

	@Override
	public MeritEntryControllerAPI getController(int index) {
		return entryControllers.get(index);
	}

	@Override
	public Iterator<MeritEntryControllerAPI> iterator() {
		return entryControllers.iterator();
	}
	
}
