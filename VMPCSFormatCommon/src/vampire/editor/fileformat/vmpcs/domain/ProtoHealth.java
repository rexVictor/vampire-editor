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

import vampire.editor.plugin.api.domain.sheet.Health;
import vampire.editor.plugin.api.domain.sheet.HealthEntry;

public class ProtoHealth implements MapId, MapIdChilds, ToRealModelTransformable<Health>{
	
	private Integer mapid;
	
	private List<ProtoHealthEntry> levels = new LinkedList<>();
	
	@JsonIgnore
	private Health health;

	public ProtoHealth() {
		super();
	}
	
	public ProtoHealth(Health health, IntegerWrap integerWrap){
		this.health = health;
		this.mapid = integerWrap.getInt();
		for (HealthEntry entry : health){
			levels.add(new ProtoHealthEntry(entry, integerWrap));
		}
	}

	@Override
	public Integer getMapid() {
		return mapid;
	}

	public void setMapid(Integer mapid) {
		this.mapid = mapid;
	}

	public List<ProtoHealthEntry> getLevels() {
		return levels;
	}

	public void setLevels(List<ProtoHealthEntry> levels) {
		this.levels = levels;
	}
	
	public void addLevel(ProtoHealthEntry level){
		levels.add(level);
	}
	
	public ProtoHealthEntry getLevel(Integer i){
		return levels.get(i);
	}

	@JsonIgnore
	@Override
	public List<? extends MapId> getMapIdChilds() {
		return levels;
	}

	@Override
	public Health toRealModel() {
		if (health == null){
			health = Constructors.constructors.createHealth();
			for (ProtoHealthEntry protoHealthEntry : levels){
				health.add(protoHealthEntry.toRealModel());
			}
		}
		return health;
	}
	
	

}
