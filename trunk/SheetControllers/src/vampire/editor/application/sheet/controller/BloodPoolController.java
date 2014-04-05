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

import vampire.editor.application.sheet.events.BloodPoolEvent;
import vampire.editor.plugin.api.application.sheet.controller.BloodPoolControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.ControllerVisitor;
import vampire.editor.plugin.api.application.sheet.events.BloodPoolListener;
import vampire.editor.plugin.api.domain.sheet.BloodPool;
import vampire.editor.plugin.api.view.events.BloodPoolViewEvent;
import vampire.editor.plugin.api.view.events.BloodPoolViewListener;
import vampire.editor.plugin.api.view.sheet.BloodPoolView;

public class BloodPoolController extends AbstractController<BloodPool, BloodPoolView, BloodPoolListener> 
								implements BloodPoolViewListener, BloodPoolControllerAPI{
	
	public BloodPoolController(BloodPool bloodPool, BloodPoolView view) {
		super(bloodPool, view);
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
	public void setValue(int value) {
		int formerValue = model.getValue();
		int maxValue = model.getMaxValue();
		BloodPoolEvent event = new BloodPoolEvent(this, formerValue, value, maxValue, maxValue); 
		lock.lock();
		try{
			model.setValue(value);
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
		int formerValue = model.getValue();
		int formerMaxValue = model.getMaxValue();
		BloodPoolEvent event = new BloodPoolEvent(this, formerValue, formerValue, formerMaxValue, maxValue); 
		lock.lock();
		try{
			model.setMaxValue(maxValue);
			view.setMaxValue(maxValue);
			for(BloodPoolListener l : listeners){
				l.maxValueChanged(event);
			}
		}
		finally{
			lock.unlock();
		}
	}

	@Override
	public void accept(ControllerVisitor visitor) {
		visitor.visit(this);
	}
	
}
