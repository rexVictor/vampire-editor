/*******************************************************************************
 * The Sheetview of the vampire editor implemented using Swing.
 * Copyright (C) 2013 Matthias Reimchen
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
 * For further information see https://code.google.com/p/vampire-editor/ or write a
 * mail to development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.gui.swing.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.gui.swing.application.Initializer;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;
import vampire.editor.plugin.api.view.events.ValueViewListener;
import vampire.editor.plugin.api.view.sheet.ValueView;
import static vampire.editor.gui.swing.view.valueviews.AbstractValueView.CIRCLE_BLACK;
import static vampire.editor.gui.swing.view.valueviews.AbstractValueView.CIRCLE_WHITE;


public class SValueView implements ValueView{
	

	
	private class Clicker extends MouseAdapter{
		
		@Override
		public void mouseClicked(MouseEvent event){
			int clicked = circles.indexOf(event.getSource());
			switch (event.getButton()) {
			case MouseEvent.BUTTON1:  if (clicked+1 != value) clicked++; break;
			case MouseEvent.BUTTON3:  break;
			}
			if (!event.isControlDown())
				setValue0(clicked);
			else
				setTempValue0(clicked);
		}
		
	}
	
	private final MouseListener circleListener = new Clicker();
	
	private final JPanel panel = new JPanel();
	
	private final List<ValueViewListener> listeners = new LinkedList<>();
	
	private final List<JLabel> circles = new ArrayList<>(10);
	
	private final JLabel space = new JLabel(" ");
	
	private int value = -19834;
	
	private int tempValue = -53573;
	
	private final ValueViewAttributes atts;
	
	private final FormLayout layout = new FormLayout();
	
	public SValueView(ValueViewAttributes atts){
		this.atts = atts;
		initialize();
	}
	
	
	private void initialize(){
		panel.setLayout(layout);
		layout.appendRow(RowSpec.decode("pref"));
		Initializer.initialize(panel);
		for (int i = 0; i < atts.getCircles(); i++){
			addCircle0();
		}
		redraw();
	}
	
	private void redraw(){
		int tempValue = this.tempValue;
		int value = this.value;
		if (value < 0) value = 0;
		if (tempValue<value){
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
	
	
	public void setValue0(int value) {
		this.value = value;
		redraw();
		SValueViewEvent event = new SValueViewEvent(value, tempValue);
		for (ValueViewListener l : listeners){
			l.valueChanged(event);
		}
	}
	
	@Override
	public void setValue(int value) {
		this.value = value;
		redraw();
	}

	public void setTempValue0(int value) {
		this.tempValue = value;
		redraw();
		SValueViewEvent event = new SValueViewEvent(value, tempValue);
		for (ValueViewListener l : listeners){
			l.tempValueChanged(event);
		}
	}
	
	@Override
	public void setTempValue(int value){
		this.tempValue = value;
		redraw();
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
			layout.appendColumn(ColumnSpec.decode("pref:GROW"));
			CellConstraints constraints = new CellConstraints();
			constraints.gridHeight	=	1;
			constraints.gridWidth	=	1;
			constraints.gridX		=	layout.getColumnCount();
			constraints.gridY		=	1;
			constraints.hAlign		=	CellConstraints.FILL;
			
			panel.add(space, constraints);			
		}
		JLabel newCircle = new JLabel();
		newCircle.setFont(new Font(Font.SERIF, 0, 14));
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	layout.getColumnCount();
		constraints.gridY		=	1;
		constraints.hAlign		=	CellConstraints.FILL;
		newCircle.addMouseListener(circleListener);
		circles.add(newCircle);
		panel.add(newCircle, constraints);
		
	}
	
	public JPanel getView(){
		return panel;
	}
	
	public ValueView clone(){
		return null;
	}


	@Override
	public ValueViewAttributesAPI getViewAttributes() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void setToolTip(int circle, String text) {}
	
	
	


}
