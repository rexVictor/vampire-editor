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

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

/**
 * The ValueView for noSpace, dynamic and noSquares
 * @author rex_victor
 *
 */
class DefaultValueView extends AbstractValueView{
	
	/**
	 * The ValueView for noSpace, dynamic and noSquares
	 */
	DefaultValueView(ValueViewAttributes viewAtts) {
		super(viewAtts);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(Box.createGlue());
		for (int i = 0; i < viewAtts.getCircles(); i++){
			addCircle0();
		}
	}

	@Override
	protected void addCircle0() {
		JLabel label = new JLabel();
		label.setIcon(circleWhite);
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.addMouseListener(new ValueClickListener(this, circles.size()));
		label.addMouseListener(new StrgTempClickListener(this, circles.size()));
		circles.add(label);
		
		panel.add(label);
		panel.add(Box.createGlue());
	}

	@Override
	public void addCircle() {
		addCircle0();
		viewAtts.setCircles(viewAtts.getCircles()+1);
		panel.revalidate();
	}

	@Override
	public void removeCircle() {
		removeCircle0();
		viewAtts.setCircles(viewAtts.getCircles()-1);
		panel.revalidate();
	}

	@Override
	protected void removeCircle0() {
		int lastIndex = circles.size()-1;
		JLabel last = circles.remove(lastIndex);
		getPanel().remove(panel.getComponentCount()-1);
		getPanel().remove(last);
	}
	
	public DefaultValueView clone(){
		ValueViewAttributes clone = viewAtts.clone();
		return new DefaultValueView(clone);
	}
	
}
