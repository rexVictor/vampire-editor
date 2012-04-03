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

public class SValueView implements ValueView{
	
	static final String CIRCLE_WHITE = "\u25CB";
	
	static final String CIRCLE_BLACK = "\u25CF";
	
	private class Clicker extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent event){
			int index = circles.indexOf(event.getSource());
			if (event.isControlDown()){
				switch (event.getButton()){
				case MouseEvent.BUTTON1 : setTempValue(index+1); break;
				case MouseEvent.BUTTON3 : setTempValue(index); break;
				}
			}
			else{
				switch (event.getButton()){
				case MouseEvent.BUTTON1 : setValue(index+1); break;
				case MouseEvent.BUTTON3 : setValue(index); break;
			}
			}
			
			
		}
	}
	
	private final MouseListener circleListener = new Clicker();
	
	private final JPanel panel = new JPanel();
	
	private final List<ValueViewListener> listeners 
						= new LinkedList<>();
						
	private final List<JLabel> circles = new ArrayList<>();
	
	private int value;
	
	private int tempValue;
	
	private void initialize(){
		GridLayout layout = new GridLayout(1, 0);
		panel.setLayout(layout);
		for (int i = 0; i<10; i++){
			JLabel circle = new JLabel();
			circle.setText(CIRCLE_WHITE);
			circle.addMouseListener(circleListener);
			circles.add(circle);
			panel.add(circle);
		}
	}
	
	public SValueView(){
		initialize();
	}

	@Override
	public void setValue(int value) {
		for (int i = 0; i<value; i++){
			circles.get(i).setText(CIRCLE_BLACK);
			circles.get(i).setForeground(Color.BLACK);
		}
		for (int i = value; i<10; i++){
			circles.get(i).setText(CIRCLE_WHITE);
			circles.get(i).setForeground(Color.BLACK);
		}
		this.value = value;
		
		for (ValueViewListener l : listeners){
			SValueViewEvent event = new SValueViewEvent(value, tempValue);
			l.valueChanged(event);
		}
		
	}

	@Override
	public void setTempValue(int tempValue) {
		this.tempValue = tempValue;
		if (tempValue>value){
			setValue(value);
			for (int i = value; i<tempValue; i++){
				circles.get(i).setText(CIRCLE_BLACK);
				circles.get(i).setForeground(Color.BLUE);
			}
		}
		if (tempValue<value){
			setValue(value);
			for (int i = tempValue; i < value; i++){
				circles.get(i).setText(CIRCLE_BLACK);
				circles.get(i).setForeground(Color.GRAY);
			}
		}
		
	}

	@Override
	public void addListener(ValueViewListener listener) {
		listeners.add(listener);
		
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	public int getCircleCount(){
		return circles.size();
	}
	
	List<JLabel> getCircles(){
		return circles;
	}

}
