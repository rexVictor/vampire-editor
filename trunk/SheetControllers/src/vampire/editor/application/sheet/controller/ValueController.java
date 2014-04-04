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

import vampire.editor.application.sheet.events.ValueEvent;
import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueListener;
import vampire.editor.plugin.api.domain.sheet.Value;
import vampire.editor.plugin.api.view.events.ValueViewEvent;
import vampire.editor.plugin.api.view.events.ValueViewListener;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class ValueController extends AbstractController<Value, ValueView, ValueListener>
							implements ValueViewListener, ValueControllerAPI{
	
	public ValueController(Value value, ValueView valueView) {
		super(value, valueView);
		valueView.addListener(this);
	}
	
	@Override
	public void setValue(int value){
		ValueEvent event = new ValueEvent(this, model.getValue(), value, model.getTempValue(), model.getTempValue());
		lock.lock();
		try {
			this.model.setValue(value);
			this.model.setTempValue(value);
			model.setValue(value);
			model.setTempValue(value);
			for (ValueListener l : listeners){
				l.valueChanged(event);
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	@Override
	public void setTempValue(int value){
		
		ValueEvent event = new ValueEvent(this, this.model.getValue(), this.model.getValue(), this.model.getTempValue(), value);
		
		lock.lock();
		try {
			this.model.setTempValue(value);
			model.setTempValue(value);
			for (ValueListener l : listeners){
				l.tempValueChanged(event);
			}
		}
		finally {
			lock.unlock();
		}		
		
	}
	
	@Override
	public void valueChanged(ValueViewEvent viewEvent) {
		setValue(viewEvent.getValue());
		
	}

	@Override
	public void tempValueChanged(ValueViewEvent viewEvent) {
		setTempValue(viewEvent.getTempValue());
		
	}
	
	@Override
	public ValueController clone(){
		Value cloneValue = model.clone();
		cloneValue.setTempValue(-1);
		cloneValue.setValue(0);
		ValueView cloneView = view.clone();
		return new ValueController(cloneValue, cloneView);
	}

}
