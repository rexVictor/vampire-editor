package vampire.editor.gui.swing.view.valueviews;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vampire.editor.domain.sheet.view.ValueViewAttributes;
import vampire.editor.gui.swing.view.SValueViewEvent;
import vampire.editor.plugin.api.view.events.ValueViewListener;
import vampire.editor.plugin.api.view.sheet.ValueView;

public abstract class AbstractValueView implements ValueView{
	
	public static final String CIRCLE_BLACK 	=	"\u25CF";
	
	public static final String CIRCLE_WHITE 	=	"\u25CB";
	
	public static final String SQUARE_EMPTY		=	"\u2610";
	
	public static final String SQUARE_CROSSED	=	"\u2612";
	
	
	public static AbstractValueView getValueView(ValueViewAttributes viewAtts){
		if (viewAtts.isTempSquared()){
			return new SquaredValueView(viewAtts);
		}
		if (viewAtts.isDynamic() && viewAtts.isShowSpace()){
			return new SpacedValueView(viewAtts);
		}
		if (!(viewAtts.isDynamic()) && !(viewAtts.isShowSpace())){
			return new StaticValueView(viewAtts);
		}
		return null;
	}
	
	protected final List<JLabel> circles = new ArrayList<>();
	
	private final List<ValueViewListener> listeners = new LinkedList<>();
	
	protected final Lock lock = new ReentrantLock();
	
	private final JPanel panel = new JPanel();
	
	protected int tempValue;
	
	protected int value;
	
	
	protected ValueViewAttributes viewAtts;
	
	protected AbstractValueView(ValueViewAttributes viewAtts){
		panel.setBackground(Color.WHITE);
		this.viewAtts = viewAtts;
	}
	
	
	
	public abstract void addCircle();
	
	protected abstract void addCircle0();
	
	@Override
	public void addListener(ValueViewListener listener) {
		lock.lock();
		try{
			listeners.add(listener);
		}
		finally{
			lock.unlock();
		}
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	protected void redraw(){
		redrawValue();
		redrawTempValue();
	}
	
	protected void redrawTempValue(){
		int tempValue = this.tempValue;
		if (tempValue == value || tempValue == -1){
			tempValue = 0;
			this.tempValue = -1;
		}
		for (int i = 0; i < tempValue; i++) {
			circles.get(i).setBackground(Color.LIGHT_GRAY);
		}
		for (int i = tempValue; i < circles.size(); i++){
			circles.get(i).setBackground(Color.WHITE);
		}
	}


	protected void redrawValue(){
		for (int i = 0; i < value; i++){
			circles.get(i).setText(CIRCLE_BLACK);
		}
		for (int i = value; i < circles.size(); i++){
			circles.get(i).setText(CIRCLE_WHITE);
		}
	}
	
	public abstract void removeCircle();
	
	protected abstract void removeCircle0();
	
	@Override
	public void setTempValue(int value) {
		this.tempValue = value;
		redraw();
	}
	
	protected void setTempValue0(int value){
		setTempValue(value);
		SValueViewEvent viewEvent = new SValueViewEvent(value, tempValue);
		lock.lock();
		try{
			for (ValueViewListener l : listeners) {
				l.tempValueChanged(viewEvent);
			}
		}
		finally{
			lock.unlock();
		}
	}
	
	@Override
	public void setValue(int value) {
		this.value = value;
		redraw();
	}
	
	protected void setValue0(int value){
		setValue(value);
		SValueViewEvent viewEvent = new SValueViewEvent(value, tempValue);
		lock.lock();
		try{
			for (ValueViewListener l : listeners) {
				l.valueChanged(viewEvent);
			}
		}
		finally{
			lock.unlock();
		}
	}

}
