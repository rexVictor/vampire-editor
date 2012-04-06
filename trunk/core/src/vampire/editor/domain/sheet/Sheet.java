package vampire.editor.domain.sheet;

import java.lang.reflect.Field;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;
import vampire.editor.plugin.api.domain.sheet.Nameable;

public class Sheet {
	
	private Data<MetaEntryAPI, MetaEntry> meta;
	
	private Data<CategoryAPI, Category> categories;
	
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

	public Data<MetaEntryAPI, MetaEntry> getMeta() {
		return meta;
	}

	public Data<CategoryAPI, Category> getCategories() {
		return categories;
	}
	
	
	
	
	

}
