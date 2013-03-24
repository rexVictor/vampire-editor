/*******************************************************************************
 * The Sheetview of the vampire editor implemended using Swing.
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

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;

import vampire.editor.domain.sheet.view.ValueViewAttributes;

/**
 * The ValueView for showSpace, dynamic and no Squares
 * @author rex_victor
 *
 */
class SpacedValueView extends DefaultValueView{
	
	private final JLabel space = new JLabel(" ");
	
	/**
	 * The ValueView for showSpace, dynamic and no Squares
	 */
	SpacedValueView(ValueViewAttributes viewAtts) {
		super(viewAtts);
		getPanel().setBackground(Color.WHITE);
	}
	
	@Override
	protected void addCircle0(){
		if (circles.size() == 5){
			layout.appendColumn(ColumnSpec.decode("pref"));
			CellConstraints constraints = new CellConstraints();
			constraints.gridHeight	=	1;
			constraints.gridWidth	=	1;
			constraints.gridX		=	layout.getColumnCount();
			constraints.gridY		=	1;
			
			getPanel().add(space, constraints);
		}
		super.addCircle0();
	}

	@Override
	public void removeCircle0(){
		if (circles.size()==6){
			CellConstraints constraints = layout.getConstraints(space);
			getPanel().remove(space);
			layout.removeColumn(constraints.gridX);
		}
		super.removeCircle0();
	}
	
}
