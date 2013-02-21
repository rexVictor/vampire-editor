package vampire.editor.sheetloader.common;

import java.awt.Font;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import vampire.editor.domain.sheet.Sheet;
import vampire.editor.domain.sheet.Value;
import vampire.editor.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.domain.sheet.view.MetaEntryViewAttributes;
import vampire.editor.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.domain.sheet.view.TraitViewAttributes;
import vampire.editor.domain.sheet.view.ValueViewAttributes;

public class ClassToFileMapper {
	
	static
	{
		String fonts = "fonts.json";
		String valueViewAtts = "valueviewatts.json";
		String traitViewAtts = "traitviewatts.json";
		String subCategoryViewAtts = "subcategoryviewatts.json";
		String categroyViewAtts = "categoryviewatts.json";
		String metaEntryViewAtts = "metaentryviewatts.json";
		String sheet = "sheet.json";
		String values = "values.json";
		Map<Class<?>, String> map = new HashMap<>();
		map.put(Font.class, fonts);
		map.put(ValueViewAttributes.class, valueViewAtts);
		map.put(TraitViewAttributes.class, traitViewAtts);
		map.put(SubCategoryViewAttributes.class, subCategoryViewAtts);
		map.put(CategoryViewAttributes.class, categroyViewAtts);
		map.put(MetaEntryViewAttributes.class, metaEntryViewAtts);
		map.put(Sheet.class, sheet);
		map.put(Value.class, values);
		paths = Collections.unmodifiableMap(map);		
	}
	
	public static final Map<Class<?>, String> paths;

}
