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
package vampire.editor.gui.swing.view;

import java.awt.Color;

import javax.swing.JPanel;

import vampire.editor.plugin.api.view.sheet.HealthView;

import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.FormLayout;

public class MiscView implements vampire.editor.plugin.api.view.sheet.MiscView{
	
	private final SBloodPoolView bloodPoolView;
	
	private final HorizontalHealthView healthView;
	
	private final SMeritView meritView;
	
	private final SMeritView flawView;
	
	private final JPanel panel = new JPanel();
	
	private final FormLayout layout = new FormLayout("5px, pref:GROW, 5px, pref:GROW, 5px, pref:GROW, 5px",
												"pref,pref,pref");
	
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
		layout.setColumnGroups(new int[][]{{2,4,6}});
		panel.setLayout(layout);
		panel.setBackground(Color.WHITE);
		CellConstraints constraints = new CellConstraints();
		constraints.gridX		=	2;
		constraints.gridY		=	1;
		constraints.gridHeight	=	2;
		constraints.gridWidth	=	1;
			
		panel.add(meritView.getPanel(), constraints);
		
		constraints.gridX		=	4;
		constraints.gridHeight	=	1;
		
		panel.add(bloodPoolView.getView(), constraints);
		
		constraints.gridY		=	2;
		panel.add(healthView.getPanel(), constraints);
		
		constraints.gridY		=	1;
		constraints.gridX		=	6;
		constraints.gridHeight	=	2;
		
		panel.add(flawView.getPanel(), constraints);
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
