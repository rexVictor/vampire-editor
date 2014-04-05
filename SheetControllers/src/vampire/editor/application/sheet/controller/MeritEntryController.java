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

import vampire.editor.application.sheet.events.MeritEntryEvent;
import vampire.editor.plugin.api.application.sheet.controller.ControllerVisitor;
import vampire.editor.plugin.api.application.sheet.controller.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryListener;
import vampire.editor.plugin.api.domain.sheet.Merit;
import vampire.editor.plugin.api.view.events.MeritEntryViewEvent;
import vampire.editor.plugin.api.view.events.MeritEntryViewListener;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;

public class MeritEntryController extends AbstractController<Merit, MeritEntryView, MeritEntryListener>
						implements MeritEntryControllerAPI, MeritEntryViewListener{
	
	public MeritEntryController(Merit merit, MeritEntryView view) {
		super(merit, view);
		view.addListener(this);
	}

	@Override
	public void setCost(int cost) {
		final MeritEntryEvent e = new MeritEntryEvent(this, model.getCost(), cost, model.getName(), model.getName());
		lock.lock();
		try{
			model.setCost(cost);
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
		final MeritEntryEvent e = new MeritEntryEvent(this, model.getCost(), model.getCost(), model.getName(), name);
		lock.lock();
		try{
			model.setName(name);
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
		final MeritEntryEvent e = new MeritEntryEvent(this, model.getCost(), cost, model.getName(), model.getName());
		lock.lock();
		try{
			model.setCost(cost);
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
		final MeritEntryEvent e = new MeritEntryEvent(this, model.getCost(), model.getCost(), model.getName(), name);
		lock.lock();
		try{
			model.setName(name);
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
		Merit cloneMerit = model.clone();
		MeritEntryView cloneEntryView = view.clone();
		MeritEntryController clone = new MeritEntryController(cloneMerit, cloneEntryView);
		return clone;
	}

	@Override
	public void accept(ControllerVisitor visitor) {
		visitor.visit(this);
	}
	
	
}
