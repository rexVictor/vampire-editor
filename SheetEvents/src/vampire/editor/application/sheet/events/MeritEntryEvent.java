/*******************************************************************************
 * Vampire Editor Sheet Events implementation.
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
package vampire.editor.application.sheet.events;

import vampire.editor.plugin.api.application.sheet.events.MeritEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.MeritEntryEventAPI;

public class MeritEntryEvent implements MeritEntryEventAPI{
	
	private final MeritEntryControllerAPI source;
	
	private final int formerCost;
	
	private final int newCost;
	
	private final String formerName;
	
	private final String newName;

	public MeritEntryEvent(MeritEntryControllerAPI source, int formerCost,
			int newCost, String formerName, String newName) {
		super();
		this.source = source;
		this.formerCost = formerCost;
		this.newCost = newCost;
		this.formerName = formerName;
		this.newName = newName;
	}

	@Override
	public MeritEntryControllerAPI getSource() {
		return source;
	}

	@Override
	public int getFormerCost() {
		return formerCost;
	}

	@Override
	public int getNewCost() {
		return newCost;
	}

	@Override
	public String getFormerName() {
		return formerName;
	}

	@Override
	public String getNewName() {
		return newName;
	}
	
}
