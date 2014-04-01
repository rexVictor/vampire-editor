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

import vampire.editor.plugin.api.domain.sheet.SubCategory;
import vampire.editor.plugin.api.domain.sheet.Trait;

public class ProtoSubCategory implements MapId, MapIdChilds, ToRealModelTransformable<SubCategory>{
	
	private Integer mapid;
	
	private String name;
	
	private List<ProtoTrait> traits = new LinkedList<>();
	
	@JsonIgnore
	private SubCategory subCategory;

	public ProtoSubCategory() {
		super();
	}

	public ProtoSubCategory(Integer mapid, String name) {
		super();
		this.mapid = mapid;
		this.name = name;
	}
	
	public ProtoSubCategory(SubCategory subCategory, IntegerWrap integerWrap){
		this.subCategory = subCategory;
		this.mapid = integerWrap.getInt();
		this.name = subCategory.getName();
		for (Trait trait : subCategory){
			traits.add(new ProtoTrait(trait, integerWrap));
		}
	}

	public void setMapid(Integer mapId) {
		this.mapid = mapId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTraits(List<ProtoTrait> protoTraits) {
		this.traits = protoTraits;
	}



	@Override
	public Integer getMapid() {
		return mapid;
	}

	public String getName() {
		return name;
	}

	public List<ProtoTrait> getTraits() {
		return traits;
	}
	
	public void addTrait(ProtoTrait protoTrait){
		traits.add(protoTrait);
	}

	public ProtoTrait getTrait(Integer i){
		return traits.get(i);
	}

	@JsonIgnore
	@Override
	public List<? extends MapId> getMapIdChilds() {
		return traits;
	}

	@Override
	public SubCategory toRealModel() {
		if (subCategory == null){
			subCategory = Constructors.constructors.createSubCategory();
			subCategory.setName(name);
			for (ProtoTrait protoTrait : traits){
				subCategory.add(protoTrait.toRealModel());
			}
		}
		return subCategory;
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
		if (!(obj instanceof ProtoSubCategory)) {
			return false;
		}
		ProtoSubCategory other = (ProtoSubCategory) obj;
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
