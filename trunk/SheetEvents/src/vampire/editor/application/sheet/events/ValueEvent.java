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

import vampire.editor.plugin.api.application.sheet.controller.ValueControllerAPI;
import vampire.editor.plugin.api.application.sheet.events.ValueEventAPI;

public class ValueEvent implements ValueEventAPI {
	
	private final ValueControllerAPI source;
	
	private final int formelValue;
	
	private final int newValue;
	
	private final int formerTempValue;
	
	private final int newTempValue;

	public ValueEvent(ValueControllerAPI value, int formelValue, int newValue,
			int formerTempValue, int newTempValue) {
		super();
		this.source = value;
		this.formelValue = formelValue;
		this.newValue = newValue;
		this.formerTempValue = formerTempValue;
		this.newTempValue = newTempValue;
	}

	@Override
	public ValueControllerAPI getSource() {
		return source;
	}

	@Override
	public int getFormerValue() {
		return formelValue;
	}

	@Override
	public int getNewValue() {
		return newValue;
	}

	@Override
	public int getFormerTempValue() {
		return formerTempValue;
	}

	@Override
	public int getNewTempValue() {
		return newTempValue;
	}
	
	
	

}
