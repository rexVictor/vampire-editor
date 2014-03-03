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

import java.util.ArrayList;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import vampire.editor.gui.swing.domain.Images;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

class SquaredValueView extends AbstractValueView{
	
	protected FormLayout layout = new FormLayout();
	
	protected List<JLabel> squares = new ArrayList<>();
	
	private Icon emptySquare;
	
	private Icon crossedSquare;

	SquaredValueView(ValueViewAttributes viewAtts) {
		super(viewAtts);
		tempValue = 0;
		int size = viewAtts.getSize();
		emptySquare = new ImageIcon(Images.getImage("square_empty", size, size));
		crossedSquare = new ImageIcon(Images.getImage("square_crossed", size, size));
		getPanel().setLayout(layout);
		layout.appendRow(RowSpec.decode("pref"));
		layout.appendRow(RowSpec.decode("5px"));
		layout.appendRow(RowSpec.decode("pref"));
		for (int i = 0; i < viewAtts.getCircles(); i++){
			addCircle0();
		}
	}
	
	@Override
	protected void redrawTempValue(){
		int tempValue = this.tempValue;
		for (int i = 0; i < tempValue; i++) {
			squares.get(i).setIcon(crossedSquare);
		}
		for (int i = tempValue; i < circles.size(); i++){
			squares.get(i).setIcon(emptySquare);
		}
	}

	@Override
	public void addCircle() {}

	@Override
	protected void addCircle0() {
		layout.appendColumn(ColumnSpec.decode("pref:GROW"));
		layout.addGroupedColumn(layout.getColumnCount());
		JLabel circle = new JLabel();
		circle.addMouseListener(new ValueClickListener(this, circles.size()));
		circles.add(circle);
		
		JLabel square = new JLabel();
		square.setIcon(emptySquare);
		square.addMouseListener(new SquareClickListener(this, squares.size()));
		squares.add(square);
		
		CellConstraints constraints = new CellConstraints();
		constraints.gridHeight	=	1;
		constraints.gridWidth	=	1;
		constraints.gridX		=	layout.getColumnCount();
		constraints.gridY		=	1;
		constraints.hAlign		=	CellConstraints.FILL;
		
		getPanel().add(circle, constraints);
		
		constraints.gridY	=	3;
		
		getPanel().add(square, constraints);
	}

	@Override
	public void removeCircle() {}

	@Override
	protected void removeCircle0() {
		int lastIndex = circles.size()-1;
		JLabel last = circles.remove(lastIndex);
		CellConstraints constraints = layout.getConstraints(last);
		getPanel().remove(last);
		JLabel lastSquare = squares.remove(lastIndex);
		getPanel().remove(lastSquare);
		layout.removeColumn(constraints.gridX);
	}

		

}
