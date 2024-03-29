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

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import vampire.editor.gui.swing.domain.Images;
import vampire.editor.gui.swing.view.Helper;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;

class SquaredValueView extends DefaultValueView{
	
	protected List<JLabel> squares = new ArrayList<>();
	
	private Icon emptySquare;
	
	private Icon crossedSquare;
	
	private JPanel total = new JPanel();
	
	private JPanel squarePanel = Helper.createPanel();

	SquaredValueView(ValueViewAttributes viewAtts) {
		super(viewAtts, false);
		tempValue = 0;
		int size = viewAtts.getSize();
		emptySquare = new ImageIcon(Images.getImage("square_empty", size, size). //$NON-NLS-1$
				getScaledInstance(this.circleWhite.getIconWidth(), -1, Image.SCALE_SMOOTH));
		crossedSquare = new ImageIcon(Images.getImage("square_crossed", size, size)); //$NON-NLS-1$
		
		total.setLayout(new GridLayout(2, 0));
		total.add(panel);
		total.add(squarePanel);
		
		squarePanel.setLayout(new BoxLayout(squarePanel, BoxLayout.X_AXIS));
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		
		panel.add(Box.createGlue());
		squarePanel.add(Box.createGlue());
		for (int i = 0; i < viewAtts.getCircles(); i++){
			addCircle0();
		}
	}
	
	SquaredValueView() {
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
		super.addCircle0();
		
		JLabel square = new JLabel();
		square.setIcon(emptySquare);
		square.setAlignmentX(Component.CENTER_ALIGNMENT);
		square.addMouseListener(new SquareClickListener(this, squares.size()));
		squares.add(square);
		squarePanel.add(square);
		squarePanel.add(Box.createGlue());
	}

	@Override
	public void removeCircle() {}

	@Override
	protected void removeCircle0() {
		super.removeCircle0();
		JLabel lastSquare = squares.remove(squares.size()-1);
		squarePanel.remove(squarePanel.getComponentCount()-1);
		squarePanel.remove(lastSquare);
	}
	
	@Override
	public SquaredValueView clone(){
		ValueViewAttributes clone = viewAtts.clone();
		return new SquaredValueView(clone);
	}
	
	@Override
	public JPanel getPanel(){
		return total;
	}
	
	@Override
	public AbstractValueView newInstance(ValueViewAttributes viewAtts) {
		return new SquaredValueView(viewAtts);
	}
	
}
