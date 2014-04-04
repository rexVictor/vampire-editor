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

import vampire.editor.application.sheet.events.HealthEvent;
import vampire.editor.plugin.api.application.sheet.controller.HealthControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.HealthEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.HealthEventAPI;
import vampire.editor.plugin.api.application.sheet.events.HealthListener;
import vampire.editor.plugin.api.domain.sheet.Health;
import vampire.editor.plugin.api.domain.sheet.HealthEntry;
import vampire.editor.plugin.api.domain.sheet.HealthEntryAPI;
import vampire.editor.plugin.api.view.events.HealthViewListener;
import vampire.editor.plugin.api.view.sheet.HealthEntryView;
import vampire.editor.plugin.api.view.sheet.HealthView;

public class HealthController extends AbstractNonLeafController<Health, HealthView, HealthListener, 
					HealthEntryControllerAPI, HealthEntryAPI, HealthEntry, HealthEntryView, HealthEventAPI>
							implements HealthControllerAPI, HealthViewListener{
	
	public HealthController(Health health, HealthView view) {
		super(health, view);
		view.addListener(this);
	}

	@Override
	protected HealthEventAPI generateEvent(HealthEntryControllerAPI reason,
			int index) {
		return new HealthEvent(this, reason, index);
	}

}
