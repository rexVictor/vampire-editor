package vampire.editor.application.sheet.controller;

import vampire.editor.plugin.api.application.sheet.controller.MiscControllerAPI;
import vampire.editor.plugin.api.view.sheet.MiscView;

public class MiscController implements MiscControllerAPI {
	
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

	@Override
	public MeritsController getMerits() {
		return merits;
	}

	@Override
	public MeritsController getFlaws() {
		return flaws;
	}

	@Override
	public HealthController getHealth() {
		return health;
	}

	@Override
	public BloodPoolController getBloodPool() {
		return bloodPool;
	}
	
	@Override
	public MiscView getView(){
		return view;
	}
	
	
	
	
}
