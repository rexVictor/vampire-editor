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

import vampire.editor.plugin.api.application.sheet.controller.BloodPoolControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.BloodPoolEventAPI;

public class BloodPoolEvent implements BloodPoolEventAPI{
	
	private final BloodPoolControllerAPI source;
	
	private final int formerValue;
	
	private final int newValue;
	
	private final int formerMaxValue;
	
	private final int newMaxValue;
	
	

	public BloodPoolEvent(BloodPoolControllerAPI source, int formerValue,
			int newValue, int formerMaxValue, int newMaxValue) {
		super();
		this.source = source;
		this.formerValue = formerValue;
		this.newValue = newValue;
		this.formerMaxValue = formerMaxValue;
		this.newMaxValue = newMaxValue;
	}

	@Override
	public BloodPoolControllerAPI getSource() {
		return source;
	}

	@Override
	public int getFormerValue() {
		return formerValue;
	}

	@Override
	public int getNewValue() {
		return newValue;
	}

	@Override
	public int getFormerMaxValue() {
		return formerMaxValue;
	}

	@Override
	public int getNewMaxValue() {
		return newMaxValue;
	}
	
	

}
