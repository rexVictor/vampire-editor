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
