/*******************************************************************************
 * Vampire Editor Model Implementation.
 * Copyright (C) 2013  Matthias Johannes Reimchen
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
package vampire.editor.domain.sheet;

import vampire.editor.plugin.api.domain.sheet.DamageType;
import vampire.editor.plugin.api.domain.sheet.HealthEntry;

/**
 * HealthEntry stores the penalty of the dice pool, the name of the wounded level and the current damage type.
 * @author rex_victor
 */
class MHealthEntry implements HealthEntry {
	
	private int penalty;
	
	private String name;
	
	private DamageType damageType;
	
	

	public MHealthEntry() {
		super();
	}

	public MHealthEntry(int penalty, String name, DamageType damageType) {
		super();
		this.penalty = penalty;
		this.name = name;
		this.damageType = damageType;
	}

	@Override
	public int getPenalty() {
		return penalty;
	}

	
	@Override
	public void setPenalty(int penalty) {
		this.penalty = penalty;
	}

	@Override
	public String getName() {
		return name;
	}

	
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public DamageType getDamageType() {
		return damageType;
	}

	
	@Override
	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}
	
	@Override
	public MHealthEntry clone(){
		return new MHealthEntry(penalty, name, damageType);
	}

}
