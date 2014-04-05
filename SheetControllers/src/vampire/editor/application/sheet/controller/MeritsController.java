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

import vampire.editor.application.sheet.events.MeritsEvent;
import vampire.editor.plugin.api.application.sheet.controller.ControllerVisitor;
import vampire.editor.plugin.api.application.sheet.controller.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.controller.MeritsControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritsEventAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritsListener;
import vampire.editor.plugin.api.domain.sheet.Merit;
import vampire.editor.plugin.api.domain.sheet.MeritAPI;
import vampire.editor.plugin.api.domain.sheet.Merits;
import vampire.editor.plugin.api.view.sheet.MeritEntryView;
import vampire.editor.plugin.api.view.sheet.MeritView;

public class MeritsController extends AbstractNonLeafController<Merits, MeritView, MeritsListener,
												MeritEntryControllerAPI, MeritAPI, Merit, MeritEntryView, MeritsEventAPI>
				implements MeritsControllerAPI{
	
	public MeritsController(Merits merits, MeritView view) {
		super(merits, view);
	}

	@Override
	protected MeritsEventAPI generateEvent(MeritEntryControllerAPI reason,
			int index) {
		return new MeritsEvent(this, reason, index);
	}

	@Override
	public void accept(ControllerVisitor visitor) {
		visitor.visit(this);
	}

	
}
