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

import vampire.editor.plugin.api.domain.sheet.Categories;
import vampire.editor.plugin.api.domain.sheet.Category;
import vampire.editor.plugin.api.domain.sheet.Meta;
import vampire.editor.plugin.api.domain.sheet.MetaEntry;
import vampire.editor.plugin.api.domain.sheet.Sheet;

public class ProtoSheet implements MapIdChilds, ToRealModelTransformable<Sheet>{
	
	private String version;
	
	private String border;
	
	private List<ProtoCategory> traits;
	
	private ProtoMerits merits;
	
	private ProtoMerits flaws;
	
	private ProtoHealth health;
	
	private ProtoBloodPool bloodpool;
	
	private List<ProtoMetaEntry> meta;
	
	@JsonIgnore
	private Sheet sheet;

	public ProtoSheet() {
		super();
	}
	
	public ProtoSheet(Sheet sheet, IntegerWrap integerWrap){
		this.sheet = sheet;
		this.version = "";
		this.bloodpool = new ProtoBloodPool(sheet.getBloodPool(), integerWrap);
		this.border = sheet.getBorderKey();
		this.flaws = new ProtoMerits(sheet.getFlaws(), integerWrap);
		this.merits = new ProtoMerits(sheet.getMerits(), integerWrap);
		this.health = new ProtoHealth(sheet.getHealth(), integerWrap);
		this.traits = new LinkedList<>();
		for (Category category : sheet.getCategories()){
			this.traits.add(new ProtoCategory(category, integerWrap));
		}
		this.meta = new LinkedList<>();
		for (MetaEntry metaEntry : sheet.getMeta()){
			this.meta.add(new ProtoMetaEntry(metaEntry, integerWrap));
		}
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public List<ProtoCategory> getTraits() {
		return traits;
	}

	public void setTraits(List<ProtoCategory> traits) {
		this.traits = traits;
	}

	public ProtoMerits getMerits() {
		return merits;
	}

	public void setMerits(ProtoMerits merits) {
		this.merits = merits;
	}

	public ProtoMerits getFlaws() {
		return flaws;
	}

	public void setFlaws(ProtoMerits flaws) {
		this.flaws = flaws;
	}

	public ProtoHealth getHealth() {
		return health;
	}

	public void setHealth(ProtoHealth health) {
		this.health = health;
	}

	public ProtoBloodPool getBloodpool() {
		return bloodpool;
	}

	public void setBloodpool(ProtoBloodPool bloodpool) {
		this.bloodpool = bloodpool;
	}

	public List<ProtoMetaEntry> getMeta() {
		return meta;
	}

	public void setMeta(List<ProtoMetaEntry> meta) {
		this.meta = meta;
	}
	
	public void addMeta(ProtoMetaEntry entry){
		meta.add(entry);
	}
	
	public ProtoMetaEntry getMeta(Integer i){
		return meta.get(i);
	}

	@Override
	public String toString() {
		return "ProtoSheet [version=" + version + ", border=" + border
				+ ", traits=" + traits + ", merits=" + merits + ", flaws="
				+ flaws + ", health=" + health + ", bloodpool=" + bloodpool
				+ ", meta=" + meta + "]";
	}

	@JsonIgnore
	@Override
	public List<? extends MapId> getMapIdChilds() {
		List<MapId> list = new LinkedList<>();
		list.addAll(traits);
		list.addAll(meta);
		list.add(health);
		list.add(merits);
		list.add(bloodpool);
		list.add(flaws);
		return list;
	}

	@Override
	public Sheet toRealModel() {
		if (sheet == null){
			sheet = Constructors.constructors.createSheet();
			
			Meta meta = Constructors.constructors.createMeta();
			sheet.setMeta(meta);
			for (ProtoMetaEntry protoMetaEntry : this.meta){
				meta.add(protoMetaEntry.toRealModel());
			}
			
			Categories categories = Constructors.constructors.createCategories();
			sheet.setCategories(categories);
			for (ProtoCategory protoCategory : this.traits){
				categories.add(protoCategory.toRealModel());
			}
			
			sheet.setBloodPool(bloodpool.toRealModel());
			
			sheet.setBorderKey(border);
			
			sheet.setFlaws(flaws.toRealModel());
			sheet.setMerits(merits.toRealModel());
			sheet.setHealth(health.toRealModel());
		}
		return sheet;
	}
	
	


}
