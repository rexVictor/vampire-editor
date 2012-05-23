package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vampire.editor.plugin.api.view.events.ValueViewListener;
import vampire.editor.plugin.api.view.sheet.ValueView;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;

public class SValueView implements ValueView{
	
	public static final String CIRCLE_WHITE = "\u25CB";
	
	public static final String CIRCLE_BLACK = "\u25CF";
	
	private class Clicker extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent event){
			int clicked = circles.indexOf(event.getSource());
			switch (event.getButton()) {
			case MouseEvent.BUTTON1: clicked++; break;
			case MouseEvent.BUTTON3:  break;
			}
			if (!event.isControlDown())
				setValue(clicked);
			else
				setTempValue(clicked);
		}
		
	}
	
	private final MouseListener circleListener = new Clicker();
	
	private final JPanel panel = new JPanel();
	
	private final List<ValueViewListener> listeners = new LinkedList<>();
	
	private final List<JLabel> circles = new ArrayList<>(10);
	
	private final JLabel space = new JLabel(" ");
	
	private int value = -19834;
	
	private int tempValue = -53573;
	
	private final IValueViewAttributes atts;
	
	public SValueView(IValueViewAttributes atts){
		this.atts = atts;
		initialize();
	}
	
	
	private void initialize(){
		panel.setLayout(new GridLayout(1, 0));
		for (int i = 0; i < atts.getCircles(); i++){
			addCircle0();
		}
		redraw();
	}
	
	private void redraw(){
		int tempValue = this.tempValue;
		int value = this.value;
		if (value < 0) value = 0;
		if (tempValue<=value){
			for (int i = 0; i < tempValue; i++){
				JLabel circle = circles.get(i);
				circle.setText(CIRCLE_BLACK);
				circle.setForeground(Color.GRAY);
			}
			for (int i = Math.max(0, tempValue); i < value; i++){
				JLabel circle = circles.get(i);
				circle.setText(CIRCLE_BLACK);
				circle.setForeground(Color.BLACK);
			}
			for (int i = value; i < circles.size(); i++){
				JLabel circle = circles.get(i);
				circle.setText(CIRCLE_WHITE);
				circle.setForeground(Color.BLACK);
			}
		}
		else {
			for (int i = 0; i < value; i++){
				JLabel circle = circles.get(i);
				circle.setText(CIRCLE_BLACK);
				circle.setForeground(Color.BLACK);
			}
			for (int i = value; i < tempValue; i++) {
				JLabel circle = circles.get(i);
				circle.setText(CIRCLE_BLACK);
				circle.setForeground(Color.BLUE);
			}
			for (int i = tempValue; i < circles.size(); i++){
				JLabel circle = circles.get(i);
				circle.setText(CIRCLE_WHITE);
				circle.setForeground(Color.BLACK);
			}
		}
	}
	
	@Override
	public void setValue(int value) {
		if (this.value == value) value--;
		this.value = value;
		redraw();
		SValueViewEvent event = new SValueViewEvent(value, tempValue);
		for (ValueViewListener l : listeners){
			l.valueChanged(event);
		}
	}

	@Override
	public void setTempValue(int value) {
		if (this.tempValue == value) value --;
		this.tempValue = value;
		redraw();
		SValueViewEvent event = new SValueViewEvent(value, tempValue);
		for (ValueViewListener l : listeners){
			l.tempValueChanged(event);
		}
	}

	@Override
	public void addListener(ValueViewListener listener) {
		listeners.add(listener);		
	}
	
	public void addCircle(){
		if (atts.isDynamic() && circles.size()<10){
			addCircle0();
			redraw();
			atts.setCircles(atts.getCircles()+1);
		}
	}
	
	public void removeCircle(){
		if (atts.isDynamic() && circles.size()>5){
			removeCircle0();
			redraw();
			atts.setCircles(atts.getCircles()-1);
		}
	}
	
	private void removeCircle0(){
		JLabel toRemove = circles.get(circles.size()-1);
		circles.remove(toRemove);
		panel.remove(toRemove);		
		if (circles.size()==6 && atts.isShowSpace()){
			panel.remove(space);			
		}
	}
	
	private void addCircle0(){
		if (circles.size()==5 && atts.isShowSpace()){
			panel.add(space);			
		}
		JLabel newCircle = new JLabel();
		newCircle.addMouseListener(circleListener);
		circles.add(newCircle);
		panel.add(newCircle);
		
	}
	
	public JPanel getView(){
		return panel;
	}
	
	
	


}
