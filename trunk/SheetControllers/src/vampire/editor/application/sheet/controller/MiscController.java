/*******************************************************************************
 * Vampire Editor SheetControllers.
 * Copyright (C) 2014  Matthias Johannes Reimchen
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
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
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
