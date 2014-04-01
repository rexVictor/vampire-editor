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

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import vampire.editor.plugin.api.domain.sheet.Trait;


public class ProtoTrait implements MapId, MapIdChilds, ToRealModelTransformable<Trait>{
	
	private Integer mapid;
	
	private String name;
	
	private ProtoValue value;
	
	@JsonIgnore
	private Trait trait = null;

	public ProtoTrait() {
		super();
	}
	
	public ProtoTrait(Integer mapid, String name) {
		super();
		this.mapid = mapid;
		this.name = name;
	}
	
	public ProtoTrait(Trait trait, IntegerWrap integerWrap){
		this.mapid = integerWrap.getInt();
		this.name = trait.getName();
		this.trait = trait;
	}

	public void setMapid(Integer mapId) {
		this.mapid = mapId;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setValue(ProtoValue protoValue) {
		this.value = protoValue;
	}



	@Override
	public Integer getMapid() {
		return mapid;
	}

	public String getName() {
		return name;
	}

	public ProtoValue getValue() {
		return value;
	}

	@JsonIgnore
	@Override
	public List<? extends MapId> getMapIdChilds() {
		List<ProtoValue> list = new LinkedList<>();
		list.add(value);
		return list;
	}



	@Override
	public Trait toRealModel() {
		if (trait == null)
			trait = Constructors.constructors.createTrait();
			trait.setName(name);
		return trait;
	}





	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mapid == null) ? 0 : mapid.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof ProtoTrait)) {
			return false;
		}
		ProtoTrait other = (ProtoTrait) obj;
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
	
	
	

}
