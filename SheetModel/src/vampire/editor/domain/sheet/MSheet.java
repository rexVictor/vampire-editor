/*******************************************************************************
 * Vampire Editor Model Implementation.
 * Copyright (C) 2013  Matthias Johannes Reimchen
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
 * Further information can be obtained at
 * https://code.google.com/p/vampire-editor/ or via mail:
 * Matthias Johannes Reimchen
 * development.rex@gmail.com
 ******************************************************************************/
package vampire.editor.domain.sheet;

import java.lang.reflect.Field;

import vampire.editor.plugin.api.domain.sheet.BloodPool;
import vampire.editor.plugin.api.domain.sheet.Categories;
import vampire.editor.plugin.api.domain.sheet.Health;
import vampire.editor.plugin.api.domain.sheet.Merits;
import vampire.editor.plugin.api.domain.sheet.Meta;
import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.api.domain.sheet.Sheet;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;

/**
 * The implementation of {@link SheetAPI}.
 * This class is the root of the model without view attributes. It provides all methods to access
 * the whole model.<br>
 * All setters can only be executed once, since the fields can only be set once.
 * 
 * @author rex_victor
 *
 */
class MSheet implements Sheet {
	
	private Meta meta = null;
	
	private Categories categories;
	
	private Merits merits;
	
	private Merits flaws;
	
	private Health health;
	
	private BloodPool bloodPool;
	
	private String borderKey;
	
	@Override
	public String getBorderKey() {
		return borderKey;
	}


	@Override
	public void setBorderKey(String borderKey) {
		this.borderKey = borderKey;
	}


	@Override
	public MSheet clone(){
		MSheet clone = new MSheet();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field f : fields){
			try {
				f.set(clone, ((Nameable) f.get(this)).clone());
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
		}
		return clone;
	}
	

	@Override
	public void setCategories(Categories categories){
		if (this.categories == null){
			this.categories = categories;
		}
	}
	
	@Override
	public void setMeta(Meta meta){
		if (this.meta == null){
			this.meta = meta;
		}
	}
	
	@Override
	public void setBloodPool(BloodPool bloodPool){
		if (this.bloodPool == null){
			this.bloodPool = bloodPool;
		}
	}
	
	@Override
	public void setHealth(Health health){
		if (this.health == null){
			this.health = health;
		}
	}
	
	@Override
	public void setMerits(Merits merits){
		if (this.merits == null){
			this.merits = merits;
		}
	}
	
	@Override
	public void setFlaws(Merits merits){
		if (this.flaws == null){
			this.flaws = merits;
		}
	}
	

	@Override
	public Meta getMeta() {
		return meta;
	}

	@Override
	public Categories getCategories() {
		return categories;
	}

	@Override
	public Merits getMerits() {
		return merits;
	}
	@Override
	public Merits getFlaws() {
		return flaws;
	}

	@Override
	public BloodPool getBloodPool() {
		return bloodPool;
	}
	
	@Override
	public String toString(){
		return categories.toString();
	}
	
	@Override
	public Health getHealth(){
		return health;
	}
	

}
