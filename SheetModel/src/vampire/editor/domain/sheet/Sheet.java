package vampire.editor.domain.sheet;

import java.lang.reflect.Field;

import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.fullapi.sheet.ICategory;
import vampire.editor.plugin.fullapi.sheet.IData;
import vampire.editor.plugin.fullapi.sheet.IMetaEntry;
import vampire.editor.plugin.fullapi.sheet.ISheet;

public class Sheet implements ISheet {
	
	private IData<IMetaEntry> meta = new Data<>(null);
	
	private IData<ICategory> categories;
	
	public ISheet clone(){
		ISheet clone = new Sheet();
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

	public IData<IMetaEntry> getMeta() {
		return meta;
	}

	public IData<ICategory> getCategories() {
		return categories;
	}
	
	
	
	
	

}
