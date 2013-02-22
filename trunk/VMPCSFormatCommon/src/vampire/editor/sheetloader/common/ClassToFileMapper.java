package vampire.editor.sheetloader.common;

import java.awt.Font;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import vampire.editor.domain.sheet.Sheet;
import vampire.editor.domain.sheet.Value;
import vampire.editor.domain.sheet.view.BloodPoolViewAttributes;
import vampire.editor.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.domain.sheet.view.HealthEntryViewAttributes;
import vampire.editor.domain.sheet.view.HealthViewAttributes;
import vampire.editor.domain.sheet.view.MeritEntryViewAttibutes;
import vampire.editor.domain.sheet.view.MeritViewAttributes;
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
		String bloodPoolViewAtts = "bloodpoolviewatts.json";
		String healtEntryViewAtts = "healthentryviewatts.json";
		String healthViewAtts = "healthviewatts.json";
		String meritViewAtts = "meritviewatts.json";
		String meritEntryViewAtts = "meritentryviewatts.json";
		String sheet = "sheet.json";
		String values = "values.json";
		
		Map<Class<?>, String> map = new HashMap<>();
		map.put(Font.class, fonts);
		map.put(ValueViewAttributes.class, valueViewAtts);
		map.put(TraitViewAttributes.class, traitViewAtts);
		map.put(SubCategoryViewAttributes.class, subCategoryViewAtts);
		map.put(CategoryViewAttributes.class, categroyViewAtts);
		map.put(MetaEntryViewAttributes.class, metaEntryViewAtts);
		map.put(HealthEntryViewAttributes.class, healtEntryViewAtts);
		map.put(HealthViewAttributes.class, healthViewAtts);
		map.put(MeritViewAttributes.class, meritViewAtts);
		map.put(MeritEntryViewAttibutes.class, meritEntryViewAtts);
		map.put(BloodPoolViewAttributes.class, bloodPoolViewAtts);
		map.put(Sheet.class, sheet);
		map.put(Value.class, values);
		paths = Collections.unmodifiableMap(map);		
	}
	
	public static final Map<Class<?>, String> paths;

}
