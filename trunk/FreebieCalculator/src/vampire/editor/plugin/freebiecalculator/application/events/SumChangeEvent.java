package vampire.editor.plugin.freebiecalculator.application.events;

import vampire.editor.plugin.freebiecalculator.application.controllers.FSubCatController;

public class SumChangeEvent {
	
	private final FSubCatController controller;
	
	private final int formerSum;
	
	private final int newSum;

	public SumChangeEvent(FSubCatController controller, int formerSum,
			int newSum) {
		super();
		this.controller = controller;
		this.formerSum = formerSum;
		this.newSum = newSum;
	}

	public FSubCatController getController() {
		return controller;
	}

	public int getFormerSum() {
		return formerSum;
	}

	public int getNewSum() {
		return newSum;
	}
	

}
