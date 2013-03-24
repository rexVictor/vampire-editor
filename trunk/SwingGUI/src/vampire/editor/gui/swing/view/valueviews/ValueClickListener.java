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

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


class ValueClickListener implements MouseListener{
	
	private final AbstractValueView valueView;
	
	private final int position;
	
	ValueClickListener(AbstractValueView valueView, int position) {
		super();
		this.valueView = valueView;
		this.position = position;
	}

	@Override
	public void mouseClicked(MouseEvent event) {
		if(!event.isControlDown()){
			switch (event.getButton()) {
			case MouseEvent.BUTTON1 : 
				valueView.setValue0(position+1);
				break;
			case MouseEvent.BUTTON3 :
				valueView.setValue0(position);
				break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}


}
