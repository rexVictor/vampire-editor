package vampire.editor.application.sheet.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import vampire.editor.application.sheet.events.ValueEvent;
import vampire.editor.domain.sheet.Value;
import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueListener;
import vampire.editor.plugin.api.view.events.ValueViewEvent;
import vampire.editor.plugin.api.view.events.ValueViewListener;
import vampire.editor.plugin.api.view.sheet.ValueView;

public class ValueController implements ValueViewListener, ValueControllerAPI{
	
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
	
	@Override
	public void setValue(int value){
		System.out.println("setValue Controller : "+value);
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
	
	@Override
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
	
	


	@Override
	public Value getValue(){
		return value;
	}
	
	@Override
	public ValueView getView(){
		return valueView;
	}

	@Override
	public void valueChanged(ValueViewEvent viewEvent) {
		System.out.println("valueChanged: "+viewEvent.getValue());
		setValue(viewEvent.getValue());
		
	}

	@Override
	public void tempValueChanged(ValueViewEvent viewEvent) {
		setTempValue(viewEvent.getTempValue());
		
	}
	
	@Override
	public void addListener(ValueListener listener){
		lock.lock();
		try {
			listeners.add(listener);
			
		}
		finally{
			lock.unlock();
			
		}
	}
	
	@Override
	public void removeListener(ValueListener listener){
		lock.lock();
		try {
			listeners.remove(listener);
		}
		finally {
			lock.unlock();
		}
	}

}