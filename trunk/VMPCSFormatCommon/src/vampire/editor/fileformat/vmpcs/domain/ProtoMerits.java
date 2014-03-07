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

import vampire.editor.plugin.api.domain.sheet.Merit;
import vampire.editor.plugin.api.domain.sheet.Merits;

public class ProtoMerits implements MapId, MapIdChilds, ToRealModelTransformable<Merits>{
	
	private Integer mapid;
	
	private List<ProtoMerit> entries = new LinkedList<>();
	
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Merits getRealMerits() {
		return realMerits;
	}

	public void setRealMerits(Merits realMerits) {
		this.realMerits = realMerits;
	}

	@JsonIgnore
	private Merits realMerits;

	public ProtoMerits() {
		super();
	}
	
	public ProtoMerits(Merits merits, IntegerWrap integerWrap){
		this.mapid = integerWrap.getInt();
		this.realMerits = merits;
		for (Merit merit : merits){
			entries.add(new ProtoMerit(merit, integerWrap));
		}
		this.name = merits.getName();
	}

	public Integer getMapid() {
		return mapid;
	}

	public void setMapid(Integer mapid) {
		this.mapid = mapid;
	}

	public List<ProtoMerit> getEntries() {
		return entries;
	}

	public void setEntries(List<ProtoMerit> entries) {
		this.entries = entries;
	}
	
	public void addEntry(ProtoMerit entry){
		entries.add(entry);
	}
	
	public ProtoMerit getEntry(Integer i){
		return entries.get(i);
	}

	@Override
	public String toString() {
		return "ProtoMerits [mapid=" + mapid + ", entries="
				+ entries + "]";
	}

	@JsonIgnore
	@Override
	public List<? extends MapId> getMapIdChilds() {
		return entries;
	}

	@Override
	public Merits toRealModel() {
		if (realMerits == null){
			realMerits = Constructors.constructors.createMerits();
			realMerits.setName(name);
			for (ProtoMerit protoMerit : entries){
				realMerits.add(protoMerit.toRealModel());
			}
		}
		return realMerits;
	}
	
	

}
