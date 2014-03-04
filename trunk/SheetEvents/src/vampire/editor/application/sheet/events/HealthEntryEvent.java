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

import vampire.editor.plugin.api.application.sheet.controller.HealthEntryControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.HealthEntryEventAPI;
import vampire.editor.plugin.api.domain.sheet.DamageType;

public class HealthEntryEvent implements HealthEntryEventAPI{
	
	private final HealthEntryControllerAPI source;
	
	private final int formerPenalty;
	
	private final int newPenalty;
	
	private final DamageType formerDamageType;
	
	private final DamageType newDamageType;
	
	private final String formerText;
	
	private final String newText;

	public HealthEntryEvent(HealthEntryControllerAPI source, int formerPenalty,
			int newPenalty, DamageType formerDamageType,
			DamageType newDamageType, String formerText, String newText) {
		super();
		this.source = source;
		this.formerPenalty = formerPenalty;
		this.newPenalty = newPenalty;
		this.formerDamageType = formerDamageType;
		this.newDamageType = newDamageType;
		this.formerText = formerText;
		this.newText = newText;
	}

	@Override
	public HealthEntryControllerAPI getSource() {
		return source;
	}

	@Override
	public int getFormerPenalty() {
		return formerPenalty;
	}

	@Override
	public int getNewPenalty() {
		return newPenalty;
	}

	@Override
	public DamageType getFormerDamageType() {
		return formerDamageType;
	}

	@Override
	public DamageType getNewDamageType() {
		return newDamageType;
	}

	@Override
	public String getFormerText() {
		return formerText;
	}

	@Override
	public String getNewText() {
		return newText;
	}
}
