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

class SquaredValueView extends AbstractValueView{
	
	protected List<JLabel> squares = new ArrayList<>();
	
	private Icon emptySquare;
	
	private Icon crossedSquare;
	
	private JPanel total = new JPanel();
	
	private JPanel squarePanel = Helper.createPanel();

	SquaredValueView(ValueViewAttributes viewAtts) {
		super(viewAtts);
		tempValue = 0;
		int size = viewAtts.getSize();
		emptySquare = new ImageIcon(Images.getImage("square_empty", size, size).
				getScaledInstance(this.circleWhite.getIconWidth(), -1, Image.SCALE_SMOOTH));
		crossedSquare = new ImageIcon(Images.getImage("square_crossed", size, size));
		
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
		JLabel label = new JLabel();
		label.setIcon(circleWhite);
		label.setOpaque(true);
		label.setBackground(Color.WHITE);
		label.addMouseListener(new ValueClickListener(this, circles.size()));
		label.addMouseListener(new StrgTempClickListener(this, circles.size()));
		circles.add(label);
		
		panel.add(label);
		panel.add(Box.createGlue());
		JLabel square = new JLabel();
		square.setIcon(emptySquare);
		square.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		square.addMouseListener(new SquareClickListener(this, squares.size()));
		squares.add(square);
		
		squarePanel.add(square);
		squarePanel.add(Box.createGlue());
	}

	@Override
	public void removeCircle() {}

	@Override
	protected void removeCircle0() {
		int lastIndex = circles.size()-1;
		JLabel last = circles.remove(lastIndex);
		JPanel subPanel = (JPanel) last.getParent();
		subPanel.remove(last);
		JLabel lastSquare = squares.remove(lastIndex);
		subPanel.remove(lastSquare);
		panel.remove(panel.getComponentCount()-1);
		panel.remove(subPanel);
	}
	
	public SquaredValueView clone(){
		ValueViewAttributes clone = viewAtts.clone();
		return new SquaredValueView(clone);
	}
	
	@Override
	public JPanel getPanel(){
		return total;
	}

		

}
