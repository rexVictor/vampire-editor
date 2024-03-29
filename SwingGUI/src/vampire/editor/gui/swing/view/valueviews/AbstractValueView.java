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
package vampire.editor.gui.swing.view.valueviews;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vampire.editor.gui.swing.domain.Images;
import vampire.editor.gui.swing.view.events.SValueViewEvent;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.view.events.ValueViewListener;
import vampire.editor.plugin.api.view.sheet.ValueView;

public abstract class AbstractValueView implements ValueView{
	
	public static final String CIRCLE_BLACK 	=	"\u25CF"; //$NON-NLS-1$
	
	public static final String CIRCLE_WHITE 	=	"\u25CB"; //$NON-NLS-1$
	
	public static final String SQUARE_EMPTY		=	"\u2610"; //$NON-NLS-1$
	
	public static final String SQUARE_CROSSED	=	"\u2612"; //$NON-NLS-1$
	
	private static final Map<SValueViewAtts, AbstractValueView> valueViews = new HashMap<>();
	
	static{
		valueViews.put(new SValueViewAtts(false, false, false), new StaticValueView());
		valueViews.put(new SValueViewAtts(false, false, true), new SquaredValueView());
		valueViews.put(new SValueViewAtts(false, true, false), new DefaultValueView());
		
		valueViews.put(new SValueViewAtts(true, false, false), new StaticSpacedValueView());
		
		valueViews.put(new SValueViewAtts(true, true, false), new SpacedValueView());
		
		
	}
	
	public static AbstractValueView getValueView(ValueViewAttributes viewAtts){
		SValueViewAtts atts = new SValueViewAtts(viewAtts);
		return valueViews.get(atts).newInstance(viewAtts);
	}
	
	protected final List<JLabel> circles = new ArrayList<>();
	
	private final List<ValueViewListener> listeners = new LinkedList<>();
	
	protected final JPanel panel = new JPanel();
	
	protected int tempValue = -1;
	
	protected int value;
	
	protected ImageIcon circleWhite;
	
	protected ImageIcon circleBlack;
	
	protected ImageIcon circleRedStriped;
	
	protected ImageIcon circleGreenStriped;
	
	protected ValueViewAttributes viewAtts;
	
	protected AbstractValueView(ValueViewAttributes viewAtts){
		panel.setBackground(Color.WHITE);
		this.viewAtts = viewAtts;
		int size = viewAtts.getSize();
		
		circleBlack = new ImageIcon(Images.getImage("circle_filled", size, size)); //$NON-NLS-1$
		circleWhite = new ImageIcon(Images.getImage("circle_empty", size, size)); //$NON-NLS-1$
		circleRedStriped = new ImageIcon(Images.getImage("circle_temp_lower", size, size)); //$NON-NLS-1$
		circleGreenStriped = new ImageIcon(Images.getImage("circle_temp_greater", size, size)); //$NON-NLS-1$
	}
	
	protected AbstractValueView(){
		
	}
	
	@Override
	public abstract void addCircle();
	
	protected abstract void addCircle0();
	
	@Override
	public void addListener(ValueViewListener listener) {
		listeners.add(listener);
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	protected void redraw(){
		redrawValue();
		redrawTempValue();
	}
	
	protected void redrawTempValue(){
		int localTempValue = this.tempValue;
		if (localTempValue == value || localTempValue == -1){
			localTempValue = 0;
			this.tempValue = -1;
		}
		if (localTempValue < value && this.tempValue != -1){
			for (int i = localTempValue; i < value; i++){
				circles.get(i).setIcon(circleRedStriped);
			}
		}
		else{
			for (int i = value; i < localTempValue; i++){
				circles.get(i).setIcon(circleGreenStriped);
			}
		}
			
	}

	protected void redrawValue(){
		for (int i = 0; i < value; i++){
			circles.get(i).setIcon(circleBlack);
		}
		for (int i = value; i < circles.size(); i++){
			circles.get(i).setIcon(circleWhite);
		}
	}
	
	@Override
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
		for (ValueViewListener l : listeners) {
			l.tempValueChanged(viewEvent);
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
		for (ValueViewListener l : listeners) {
			l.valueChanged(viewEvent);
		}
	}
	
	@Override
	public abstract AbstractValueView clone();
	
	@Override
	public ValueViewAttributes getViewAttributes(){
		return viewAtts;
	}
	
	@Override
	public void setToolTip(int circle, String text){
		if (circle >= circles.size()){
			return;
		}
		JLabel label = circles.get(circle);
		if (label != null){
			label.setToolTipText(text);
		}
	}
	
	public abstract AbstractValueView newInstance(ValueViewAttributes viewAtts);
	
}
