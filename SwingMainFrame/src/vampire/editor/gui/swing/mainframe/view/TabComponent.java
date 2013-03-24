/*******************************************************************************
 * The GUI of the vampire editor implemented using Swing.
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
package vampire.editor.gui.swing.mainframe.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TabComponent{
	
	private final JLabel title = new JLabel();
	
	private final JButton closeButton = new JButton("x");
	
	private final JPanel tabComponent = new JPanel();
	
	public TabComponent(){
		tabComponent.setLayout(new BorderLayout());
		tabComponent.add(title, BorderLayout.CENTER);
		tabComponent.add(closeButton, BorderLayout.EAST);
	}

	public JPanel getComponent(){
		return tabComponent;
	}
	
	public void addCloseListener(ActionListener listener){
		closeButton.addActionListener(listener);
	}
	
	public void setTitle(String title) {
		this.title.setText(title);
	}


}
