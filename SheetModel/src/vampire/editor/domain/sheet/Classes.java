package vampire.editor.domain.sheet;

import java.util.HashMap;
import java.util.Map;

import vampire.editor.plugin.fullapi.sheet.ICategory;
import vampire.editor.plugin.fullapi.sheet.IData;
import vampire.editor.plugin.fullapi.sheet.ISheet;
import vampire.editor.plugin.fullapi.sheet.ISubCategory;
import vampire.editor.plugin.fullapi.sheet.ITrait;
import vampire.editor.plugin.fullapi.sheet.IValue;
import vampire.editor.plugin.fullapi.sheet.SheetConstructors;

public class Classes implements SheetConstructors{
	
	private final Map<Class<?>, Class<?>> clazzMap = new HashMap<>();
	
	public Classes(){
		initialize();
	}
	
	private void initialize(){
		clazzMap.put(ITrait.class, Trait.class);
		clazzMap.put(IValue.class, Value.class);
		clazzMap.put(IData.class, Data.class);
		clazzMap.put(ISubCategory.class, SubCategory.class);
		clazzMap.put(ICategory.class, Category.class);
		clazzMap.put(ISheet.class, Sheet.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> Class<? extends T> getImplementingClassOf(Class<T> interfaceClass) {
		return (Class<? extends T>) clazzMap.get(interfaceClass);
	}


}
