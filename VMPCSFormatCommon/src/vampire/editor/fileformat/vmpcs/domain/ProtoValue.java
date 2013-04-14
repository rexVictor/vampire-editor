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

import vampire.editor.plugin.api.domain.sheet.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class ProtoValue implements MapId{
	
	private Integer id;
	
	private Integer mapid;
	
	@JsonIgnore
	private Value value;

	public ProtoValue() {
		super();
	}
	
	public ProtoValue(Integer mapid, Integer id) {
		super();
		this.id = id;
		this.mapid = mapid;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public void setMapid(Integer mapId) {
		this.mapid = mapId;
	}



	public Integer getId() {
		return id;
	}

	public Integer getMapid() {
		return mapid;
	}

	@Override
	public String toString() {
		return "ProtoValue [id=" + id + ", mapid=" + mapid + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((mapid == null) ? 0 : mapid.hashCode());
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
		if (!(obj instanceof ProtoValue)) {
			return false;
		}
		ProtoValue other = (ProtoValue) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (mapid == null) {
			if (other.mapid != null) {
				return false;
			}
		} else if (!mapid.equals(other.mapid)) {
			return false;
		}
		return true;
	}

	public Value getValue() {
		return value;
	}

	public void setValue(Value value) {
		this.value = value;
	}
	
	
	
	

}
