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
	
	private final IData<IMetaEntry> meta = new Data<>();
	
	private final IData<ICategory> categories = new Data<>();
	
	private final IData<IMerit> merits = new Data<>();
	
	private final IData<IHealthEntry> bloodpool = new Data<>();
	
	
	
	@Override
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
