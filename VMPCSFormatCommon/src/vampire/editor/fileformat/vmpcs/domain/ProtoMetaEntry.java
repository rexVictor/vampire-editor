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

import vampire.editor.plugin.api.domain.sheet.MetaEntry;

public class ProtoMetaEntry implements MapId, ToRealModelTransformable<MetaEntry>{
	
	private Integer mapid;
	
	private String name;
	
	private String value;
	
	private MetaEntry realModel;
	
	public ProtoMetaEntry() {
		super();
	}

	public ProtoMetaEntry(Integer mapid, String name, String value) {
		super();
		this.mapid = mapid;
		this.name = name;
		this.value = value;
	}

	public ProtoMetaEntry(MetaEntry metaEntry, IntegerWrap integerWrap){
		this.mapid = integerWrap.getInt();
		this.realModel = metaEntry;
		this.name = metaEntry.getName();
		this.value = metaEntry.getValue();
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	@Override
	public MetaEntry toRealModel(){
		if (realModel == null){
			realModel = Constructors.constructors.createMetaEntry();
			realModel.setName(name);
			realModel.setValue(value);
		}
		return realModel;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mapid == null) ? 0 : mapid.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (!(obj instanceof ProtoMetaEntry)) {
			return false;
		}
		ProtoMetaEntry other = (ProtoMetaEntry) obj;
		return mapid == other.mapid && name.equals(other.name) && value.equals(other.value);
	}
	
	

}
