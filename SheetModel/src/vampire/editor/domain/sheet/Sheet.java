package vampire.editor.domain.sheet;

import java.lang.reflect.Field;

import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;


public class Sheet implements SheetAPI {
	
	private Data<MetaEntry> meta = null;
	
	private Data<Category> categories;
	
	private Merits merits;
	
	private Merits flaws;
	
	private Health health;
	
	private BloodPool bloodPool;
	
	
	
	@Override
	public Sheet clone(){
		Sheet clone = new Sheet();
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
	

	public void setCategories(Data<Category> categories){
		if (this.categories == null){
			this.categories = categories;
		}
	}
	
	public void setMeta(Data<MetaEntry> meta){
		if (this.meta == null){
			this.meta = meta;
		}
	}
	
	public void setBloodPool(BloodPool bloodPool){
		if (this.bloodPool == null){
			this.bloodPool = bloodPool;
		}
	}
	
	public void setHealth(Health health){
		if (this.health == null){
			this.health = health;
		}
	}
	
	public void setMerits(Merits merits){
		if (this.merits == null){
			this.merits = merits;
		}
	}
	
	public void setFlaws(Merits merits){
		if (this.flaws == null){
			this.flaws = merits;
		}
	}
	

	@Override
	public Data<MetaEntry> getMeta() {
		return meta;
	}

	@Override
	public Data<Category> getCategories() {
		return categories;
	}

	public Merits getMerits() {
		return merits;
	}
	public Merits getFlaws() {
		return flaws;
	}

	public BloodPool getBloodPool() {
		return bloodPool;
	}
	
	@Override
	public String toString(){
		return categories.toString();
	}
	
	public Health getHealth(){
		return health;
	}
	

	
	
	

}
