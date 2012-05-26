package vampire.editor.domain.sheet;

import java.lang.reflect.Field;

import vampire.editor.plugin.api.domain.sheet.Nameable;
import vampire.editor.plugin.fullapi.sheet.IHealthEntry;
import vampire.editor.plugin.fullapi.sheet.ICategory;
import vampire.editor.plugin.fullapi.sheet.IData;
import vampire.editor.plugin.fullapi.sheet.IMerit;
import vampire.editor.plugin.fullapi.sheet.IMetaEntry;
import vampire.editor.plugin.fullapi.sheet.ISheet;

public class Sheet implements ISheet {
	
	private IData<IMetaEntry> meta = null;
	
	private IData<ICategory> categories;
	
	private final IData<IMerit> merits = new Data<>();
	
	private final IData<IHealthEntry> bloodpool = new Data<>();
	
	
	
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
	
	@Override
	public void setCategories(IData<ICategory> categories){
		if (this.categories == null){
			this.categories = categories;
		}
	}
	
	@Override
	public void setMeta(IData<IMetaEntry> meta){
		if (this.meta == null){
			this.meta = meta;
		}
	}

	@Override
	public IData<IMetaEntry> getMeta() {
		return meta;
	}

	@Override
	public IData<ICategory> getCategories() {
		return categories;
	}

	public IData<IMerit> getMerits() {
		return merits;
	}

	public IData<IHealthEntry> getBloodpool() {
		return bloodpool;
	}
	
	

	
	
	

}
