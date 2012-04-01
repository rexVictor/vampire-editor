package application.sheet.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import application.sheet.events.ValueEvent;

import plugin.api.application.sheet.events.ValueListener;
import plugin.api.view.events.ValueViewEvent;
import plugin.api.view.events.ValueViewListener;
import plugin.api.view.sheet.ValueView;
import domain.sheet.Value;

public class ValueController implements ValueViewListener{
	
	private final Value value;
	
	private final ValueView valueView;
	
	private final List<ValueListener> listeners = new LinkedList<>();
	
	private final Lock lock = new ReentrantLock(true);
	
	
	
	public ValueController(Value value, ValueView valueView) {
		super();
		this.value = value;
		this.valueView = valueView;
		valueView.addListener(this);
	}
	
	public void setValue(int value){
		ValueEvent event = new ValueEvent(this, this.value.getValue(), value, this.value.getTempValue(), this.value.getTempValue());
		
		lock.lock();
		try {
			this.value.setValue(value);
			valueView.setValue(value);
			for (ValueListener l : listeners){
				l.valueChanged(event);
			}
		}
		finally{
			lock.unlock();
		}
		
		
	}
	
	public void setTempValue(int value){
		ValueEvent event = new ValueEvent(this, this.value.getValue(), this.value.getValue(), this.value.getTempValue(), value);
		
		lock.lock();
		try {
			this.value.setTempValue(value);
			valueView.setTempValue(value);
			for (ValueListener l : listeners){
				l.tempValueChanged(event);
			}
		}
		finally {
			lock.unlock();
		}		
		
	}
	
	


	public Value getValue(){
		return value;
	}
	
	public ValueView getView(){
		return valueView;
	}

	@Override
	public void valueChanged(ValueViewEvent viewEvent) {
		setValue(viewEvent.getValue());
		
	}

	@Override
	public void tempValueChanged(ValueViewEvent viewEvent) {
		setTempValue(viewEvent.getTempValue());
		
	}
	
	public void addListener(ValueListener listener){
		lock.lock();
		listeners.add(listener);
		lock.unlock();
	}
	
	public void removeListener(ValueListener listener){
		lock.lock();
		listeners.remove(listener);
		lock.unlock();
	}

}
