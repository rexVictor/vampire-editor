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

import javax.swing.JLabel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

/**
 * The ValueView for noSpace, static and no Squares
 * @author rex_victor
 *
 */
class StaticValueView extends DefaultValueView{
	
	/**
	 * The ValueView for noSpace, static and no Squares
	 */
	StaticValueView(ValueViewAttributes viewAtts) {
		super(viewAtts);
	}
	
	@Override
	protected void addCircle0(){
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		layout.addGroupedColumn(layout.getColumnCount());
		JLabel circle = new JLabel();
		circle.addMouseListener(new ValueClickListener(this, circles.size()));
		circles.add(circle);
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	layout.getColumnCount();
		constraints.gridY		=	1;
		constraints.hAlign		=	CellConstraints.FILL;
		
		getPanel().add(circle, constraints);
		
	}
	
	@Override
	public void addCircle(){}
	
	@Override
	public void removeCircle(){}

	public StaticValueView clone(){
		ValueViewAttributes clone = viewAtts.clone();
		return new StaticValueView(clone);
	}
}
