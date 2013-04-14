/*******************************************************************************
 * An importer for the vampire editor handling vmpcs format.
 * Copyright (C) 2013 Matthias Reimchen
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
 * For further information see https://code.google.com/p/vampire-editor/ or write a
 * mail to development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.fileformat.vmpcs.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vampire.editor.plugin.api.domain.sheet.DamageType;
import vampire.editor.plugin.api.domain.sheet.HealthEntry;

public class ProtoHealthEntry implements MapId, ToRealModelTransformable<HealthEntry>{
	
	private Integer mapid;
	
	private String name;
	
	private DamageType damageType;
	
	private Integer penalty;
	
	@JsonIgnore
	private HealthEntry healthEntry;

	public ProtoHealthEntry() {
		super();
	}
	
	public ProtoHealthEntry(Integer mapid, String name, DamageType damageType,
			Integer penalty) {
		super();
		this.mapid = mapid;
		this.name = name;
		this.damageType = damageType;
		this.penalty = penalty;
	}
	
	public ProtoHealthEntry(HealthEntry healthEntry, IntegerWrap integerWrap){
		this.healthEntry = healthEntry;
		this.mapid = integerWrap.getInt();
		this.damageType = healthEntry.getDamageType();
		this.penalty = healthEntry.getPenalty();
		this.name = healthEntry.getName();
	}



	public Integer getMapid() {
		return mapid;
	}

	public void setMapid(Integer mapid) {
		this.mapid = mapid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DamageType getDamageType() {
		return damageType;
	}

	public void setDamageType(DamageType damageType) {
		this.damageType = damageType;
	}

	public Integer getPenalty() {
		return penalty;
	}

	public void setPenalty(Integer penalty) {
		this.penalty = penalty;
	}

	@Override
	public String toString() {
		return "ProtoHealthEntry [mapid=" + mapid + ", name=" + name
				+ ", damageType=" + damageType + ", penalty=" + penalty + "]";
	}

	@Override
	public HealthEntry toRealModel() {
		if (healthEntry == null){
			healthEntry = Constructors.constructors.createHealthEntry();
			healthEntry.setDamageType(damageType);
			healthEntry.setName(name);
			healthEntry.setPenalty(penalty);
		}
		return healthEntry;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((damageType == null) ? 0 : damageType.hashCode());
		result = prime * result + ((mapid == null) ? 0 : mapid.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((penalty == null) ? 0 : penalty.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ProtoHealthEntry)) {
			return false;
		}
		ProtoHealthEntry other = (ProtoHealthEntry) obj;
		if (damageType != other.damageType) {
			return false;
		}
		if (mapid == null) {
			if (other.mapid != null) {
				return false;
			}
		} else if (!mapid.equals(other.mapid)) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (penalty == null) {
			if (other.penalty != null) {
				return false;
			}
		} else if (!penalty.equals(other.penalty)) {
			return false;
		}
		return true;
	}
	
	
	
	

}
