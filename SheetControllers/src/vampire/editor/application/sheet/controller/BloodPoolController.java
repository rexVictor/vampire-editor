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

import vampire.editor.application.sheet.events.BloodPoolEvent;
import vampire.editor.plugin.api.application.sheet.controller.BloodPoolControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.BloodPoolListener;
import vampire.editor.plugin.api.domain.sheet.BloodPool;
import vampire.editor.plugin.api.domain.sheet.BloodPoolAPI;
import vampire.editor.plugin.api.view.events.BloodPoolViewEvent;
import vampire.editor.plugin.api.view.events.BloodPoolViewListener;
import vampire.editor.plugin.api.view.sheet.BloodPoolView;

public class BloodPoolController implements BloodPoolViewListener, BloodPoolControllerAPI{
	
	private final BloodPool bloodPool;
	
	private final BloodPoolView view;
	
	private final List<BloodPoolListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock(true);

	public BloodPoolController(BloodPool bloodPool, BloodPoolView view) {
		super();
		this.bloodPool = bloodPool;
		this.view = view;
		view.addListener(this);
	}

	@Override
	public void valueChanged(BloodPoolViewEvent e) {
		setValue(e.getValue());
	}

	@Override
	public void maxValueChanged(BloodPoolViewEvent e) {
		setMaxValue(e.getMaxValue());
	}

	@Override
	public BloodPoolAPI getBloodPool() {
		return bloodPool;
	}

	@Override
	public BloodPoolView getBloodPoolView() {
		return view;
	}

	@Override
	public void addBloodPoolListener(BloodPoolListener listener) {
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void removeBloodPoolListener(BloodPoolListener listener) {
		lock.lock();
		try{
			listeners.remove(listener);
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void setValue(int value) {
		int formerValue = bloodPool.getValue();
		int maxValue = bloodPool.getMaxValue();
		BloodPoolEvent event = new BloodPoolEvent(this, formerValue, value, maxValue, maxValue); 
		lock.lock();
		try{
			bloodPool.setValue(value);
			view.setValue(value);
			for(BloodPoolListener l : listeners){
				l.valueChanged(event);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void setMaxValue(int maxValue) {
		int formerValue = bloodPool.getValue();
		int formerMaxValue = bloodPool.getMaxValue();
		BloodPoolEvent event = new BloodPoolEvent(this, formerValue, formerValue, formerMaxValue, maxValue); 
		lock.lock();
		try{
			bloodPool.setMaxValue(maxValue);
			view.setMaxValue(maxValue);
			for(BloodPoolListener l : listeners){
				l.maxValueChanged(event);
			}
		}
		finally{
			lock.unlock();
		}
		
	}
	
}
