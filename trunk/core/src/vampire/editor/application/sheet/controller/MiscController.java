package vampire.editor.application.sheet.controller;

import vampire.editor.plugin.api.view.sheet.MiscView;

public class MiscController {
	
	private final MeritsController merits;
	
	private final MeritsController flaws;
	
	private final HealthController health;
	
	private final BloodPoolController bloodPool;
	
	private final MiscView view;

	public MiscController(MeritsController merits, MeritsController flaws,
			HealthController health, BloodPoolController bloodPool, MiscView view) {
		super();
		this.merits = merits;
		this.flaws = flaws;
		this.health = health;
		this.bloodPool = bloodPool;
		this.view = view;
	}

	public MeritsController getMerits() {
		return merits;
	}

	public MeritsController getFlaws() {
		return flaws;
	}

	public HealthController getHealth() {
		return health;
	}

	public BloodPoolController getBloodPool() {
		return bloodPool;
	}
	
	public MiscView getView(){
		return view;
	}
	
	
	
	
}
