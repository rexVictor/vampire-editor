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
package vampire.editor.gui.swing.view;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import vampire.editor.plugin.api.view.sheet.HealthView;


public class MiscView implements vampire.editor.plugin.api.view.sheet.MiscView{
	
	private final SBloodPoolView bloodPoolView;
	
	private final HorizontalHealthView healthView;
	
	private final SMeritView meritView;
	
	private final SMeritView flawView;
	
	private final JPanel panel = Helper.createPanel();
	
	private final JPanel leftColoumn = Helper.createPanel();
	
	private final JPanel middleColoumn = Helper.createPanel();
	
	private final JPanel rightColoumn = Helper.createPanel();
	
	
	public MiscView(
			SBloodPoolView bloodPoolView, HealthView healthView,
			SMeritView meritView, SMeritView flawView) {
		super();
		this.bloodPoolView = bloodPoolView;
		this.healthView = (HorizontalHealthView) healthView;
		this.meritView = meritView;
		this.flawView = flawView;
		build();
	}
	
	
	private void build(){
		panel.setLayout(new GridLayout(1, 0, 10, 0));
		panel.add(leftColoumn);
		panel.add(middleColoumn);
		panel.add(rightColoumn);
		
		leftColoumn.setLayout(new BoxLayout(leftColoumn, BoxLayout.Y_AXIS));
		middleColoumn.setLayout(new BoxLayout(middleColoumn, BoxLayout.Y_AXIS));
		rightColoumn.setLayout(new BoxLayout(rightColoumn, BoxLayout.Y_AXIS));
		
		leftColoumn.add(meritView.getPanel());
		leftColoumn.add(Box.createGlue());
		
		Helper.setMaximumHeightToPreferredHeight(bloodPoolView.getView());
		middleColoumn.add(bloodPoolView.getView());
		middleColoumn.add(healthView.getPanel());
		middleColoumn.add(Box.createGlue());
		
		rightColoumn.add(flawView.getPanel());
		rightColoumn.add(Box.createGlue());
		
		panel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
	}
	
	public JPanel getPanel(){
		return panel;
	}


	public SBloodPoolView getBloodPoolView() {
		return bloodPoolView;
	}


	public HorizontalHealthView getHealthView() {
		return healthView;
	}


	public SMeritView getMeritsView() {
		return meritView;
	}


	public SMeritView getFlawsView() {
		return flawView;
	}
	
	
	
	

}
