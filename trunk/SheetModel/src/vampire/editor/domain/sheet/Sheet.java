package vampire.editor.domain.sheet;

import java.lang.reflect.Field;

import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.api.domain.sheet.SheetAPI;


public class Sheet implements SheetAPI {
	
	private Data<MetaEntry> meta = null;
	
	private Data<Category> categories;
	
	private final Data<Merit> merits = new Data<>();
	
	private final Data<HealthEntry> bloodpool = new Data<>();
	
	
	
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

	@Override
	public Data<MetaEntry> getMeta() {
		return meta;
	}

	@Override
	public Data<Category> getCategories() {
		return categories;
	}

	public Data<Merit> getMerits() {
		return merits;
	}

	public Data<HealthEntry> getBloodpool() {
		return bloodpool;
	}
	
	@Override
	public String toString(){
		return categories.toString();
	}

	
	
	

}
