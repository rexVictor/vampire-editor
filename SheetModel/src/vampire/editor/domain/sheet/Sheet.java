package vampire.editor.domain.sheet;

import java.lang.reflect.Field;

import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;


public class Sheet implements SheetAPI {
	
	private Meta meta = null;
	
	private Data<Category> categories;
	
	private Merits merits;
	
	private Merits flaws;
	
	private Health health;
	
	private BloodPool bloodPool;
	
	private String borderKey;
	
	
	
	public String getBorderKey() {
		return borderKey;
	}


	public void setBorderKey(String borderKey) {
		this.borderKey = borderKey;
	}


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
	
	public void setMeta(Meta meta){
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
	public Meta getMeta() {
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
