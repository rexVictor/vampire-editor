package vampire.editor.domain.sheet;

import java.lang.reflect.Field;

import vampire.editor.plugin.api.domain.sheet.CategoryAPI;
import vampire.editor.plugin.api.domain.sheet.MetaEntryAPI;

public class Sheet {
	
	private Data<MetaEntryAPI, MetaEntry> meta;
	
	private Data<CategoryAPI, Category> categories;
	
	public Sheet clone(){
		Sheet clone = new Sheet();
		Field[] fields = this.getClass().getDeclaredFields();
	
		for (Field f : fields){
			try {
				f.set(clone, f.get(this));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
				return null;
			}
		}
		return clone;
	}
	
	
	

}
