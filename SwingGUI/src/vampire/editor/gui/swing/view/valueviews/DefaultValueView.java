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

import javax.swing.JLabel;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * The ValueView for noSpace, dynamic and noSquares
 * @author rex_victor
 *
 */
class DefaultValueView extends AbstractValueView{
	
	protected final FormLayout layout = new FormLayout();
	
	/**
	 * The ValueView for noSpace, dynamic and noSquares
	 */
	DefaultValueView(ValueViewAttributes viewAtts) {
		super(viewAtts);
		layout.appendRow(RowSpec.decode("pref"));
		getPanel().setLayout(layout);
		for (int i = 0; i < viewAtts.getCircles(); i++){
			addCircle0();
		}
	}

	@Override
	protected void addCircle0() {
		layout.appendColumn(ColumnSpec.decode("min"));
		JLabel label = new JLabel();
		label.setIcon(circleWhite);
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.addMouseListener(new ValueClickListener(this, circles.size()));
		label.addMouseListener(new StrgTempClickListener(this, circles.size()));
		circles.add(label);
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	layout.getColumnCount();
		constraints.gridY		=	1;
		
		getPanel().add(label, constraints);
	}

	@Override
	public void addCircle() {
		addCircle0();
		viewAtts.setCircles(viewAtts.getCircles()+1);
	}

	@Override
	public void removeCircle() {
		removeCircle0();
		viewAtts.setCircles(viewAtts.getCircles()-1);
	}

	@Override
	protected void removeCircle0() {
		int lastIndex = circles.size()-1;
		JLabel last = circles.remove(lastIndex);
		CellConstraints constraints = layout.getConstraints(last);
		getPanel().remove(last);
		layout.removeColumn(constraints.gridX);
	}
	
}
