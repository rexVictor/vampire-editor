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

import vampire.editor.plugin.api.domain.sheet.BloodPool;


public class ProtoBloodPool implements MapId, ToRealModelTransformable<BloodPool>{
	
	private Integer mapid;
	
	private Integer value;
	
	private Integer maxValue;
	
	@JsonIgnore
	private BloodPool bloodPool;

	public ProtoBloodPool() {
		super();
	}

	public ProtoBloodPool(Integer mapid, Integer value, Integer maxValue) {
		super();
		this.mapid = mapid;
		this.value = value;
		this.maxValue = maxValue;
	}
	
	public ProtoBloodPool(BloodPool bloodPool, IntegerWrap integerWrap){
		this.bloodPool = bloodPool;
		this.value = bloodPool.getValue();
		this.maxValue = bloodPool.getMaxValue();
		this.mapid = integerWrap.getInt();
	}



	@Override
	public Integer getMapid() {
		return mapid;
	}

	public void setMapid(Integer mapid) {
		this.mapid = mapid;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Integer getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}

	@Override
	public BloodPool toRealModel() {
		if (bloodPool == null){
			bloodPool = Constructors.constructors.createBloodPool();
			bloodPool.setMaxValue(maxValue);
			bloodPool.setValue(value);
		}
		return bloodPool;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mapid == null) ? 0 : mapid.hashCode());
		result = prime * result
				+ ((maxValue == null) ? 0 : maxValue.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		if (!(obj instanceof ProtoBloodPool)) {
			return false;
		}
		ProtoBloodPool other = (ProtoBloodPool) obj;
		if (mapid == null) {
			if (other.mapid != null) {
				return false;
			}
		} else if (!mapid.equals(other.mapid)) {
			return false;
		}
		if (maxValue == null) {
			if (other.maxValue != null) {
				return false;
			}
		} else if (!maxValue.equals(other.maxValue)) {
			return false;
		}
		if (value == null) {
			if (other.value != null) {
				return false;
			}
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
	
	

}
