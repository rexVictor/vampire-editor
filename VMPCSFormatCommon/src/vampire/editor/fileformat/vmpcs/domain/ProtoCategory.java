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

import vampire.editor.plugin.api.domain.sheet.Category;
import vampire.editor.plugin.api.domain.sheet.SubCategory;

public class ProtoCategory implements MapId, MapIdChilds, ToRealModelTransformable<Category>{
	
	private Integer mapid;
	
	private String name;
	
	private List<ProtoSubCategory> subCats;
	
	@JsonIgnore
	private Category category;

	public ProtoCategory() {
		super();
	}

	public ProtoCategory(Integer mapid, String name) {
		super();
		this.mapid = mapid;
		this.name = name;
	}

	public ProtoCategory(Category category, IntegerWrap integerWrap){
		this.mapid = integerWrap.getInt();
		this.category = category;
		this.name = category.getName();
		this.subCats = new LinkedList<>();
		for (SubCategory subCategory : category){
			subCats.add(new ProtoSubCategory(subCategory, integerWrap));
		}
	}

	@Override
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

	public List<ProtoSubCategory> getSubCats() {
		return subCats;
	}

	public void setSubCats(List<ProtoSubCategory> subCategories) {
		this.subCats = subCategories;
	}
	
	public void addSubCat(ProtoSubCategory protoSubCategory){
		subCats.add(protoSubCategory);
	}
	
	public ProtoSubCategory getSubCat(Integer position){
		return subCats.get(position);
	}

	@JsonIgnore
	@Override
	public List<? extends MapId> getMapIdChilds() {
		return subCats;
	}

	@Override
	public Category toRealModel() {
		if (category == null){
			category = Constructors.constructors.createCategory();
			category.setName(name);
			for (ProtoSubCategory protoSubCategory : subCats){
				category.add(protoSubCategory.toRealModel());
			}
		}
		
		return category;
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
		if (!(obj instanceof ProtoCategory)) {
			return false;
		}
		ProtoCategory other = (ProtoCategory) obj;
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
