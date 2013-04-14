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

import vampire.editor.plugin.api.domain.sheet.Merit;

public class ProtoMerit implements MapId, ToRealModelTransformable<Merit>{
	
	private int cost;
	
	private Integer mapid;
	
	@JsonIgnore
	private Merit merit;

	private String name;
	
	public ProtoMerit() {
		super();
	}
	
	public ProtoMerit(Integer mapid, int cost, String name) {
		super();
		this.mapid = mapid;
		this.cost = cost;
		this.name = name;
	}

	public ProtoMerit(Merit merit, IntegerWrap integerWrap){
		this.mapid = integerWrap.getInt();
		this.merit = merit;
		this.cost = merit.getCost();
		this.name = merit.getName();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof ProtoMerit)) {
			return false;
		}
		ProtoMerit other = (ProtoMerit) obj;
		if (cost != other.cost) {
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
		return true;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public Integer getMapid() {
		return mapid;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cost;
		result = prime * result + ((mapid == null) ? 0 : mapid.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public void setMapid(Integer mapid) {
		this.mapid = mapid;
	}

	public void setName(String name) {
		this.name = name;
	}



	@Override
	public Merit toRealModel() {
		if (merit == null){
			merit = Constructors.constructors.createMerit();
			merit.setCost(cost);
			merit.setName(name);
		}
		return merit;
	}

	@Override
	public String toString() {
		return "ProtoMerit [mapid=" + mapid + ", cost=" + cost + ", name="
				+ name + "]";
	}
	
	

}
