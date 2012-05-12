package vampire.editor.sheetloader.application;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import vampire.editor.plugin.api.domain.sheet.view.Orientation;
import vampire.editor.plugin.fullapi.sheet.SheetConstructors;
import vampire.editor.plugin.fullapi.sheet.view.ICategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;

public class ViewParser {
	
	
	private final ObjectMapper mapper = new ObjectMapper();

	public ViewParser(SheetConstructors constructors) {
		super();
		SimpleModule module = new SimpleModule();
		module.addAbstractTypeMapping(IValueViewAttributes.class, constructors.getImplementingClassOf(IValueViewAttributes.class));
		module.addAbstractTypeMapping(ITraitViewAttributes.class, constructors.getImplementingClassOf(ITraitViewAttributes.class));
		module.addAbstractTypeMapping(ISubCategoryViewAttributes.class, constructors.getImplementingClassOf(ISubCategoryViewAttributes.class));
		module.addAbstractTypeMapping(ICategoryViewAttributes.class, constructors.getImplementingClassOf(ICategoryViewAttributes.class));
		mapper.registerModule(module);
	}
	
	public IValueViewAttributes parseValueViewAttributes(Object object){
		return mapper.convertValue(object, IValueViewAttributes.class);
	}
	
	public ITraitViewAttributes parseTraitViewAttributes(Object object){
		@SuppressWarnings("unchecked")
		Map<String, ?> map = (Map<String, ?>) object;
		String orientationString = (String) map.remove("orientation");
		Orientation orientation = Orientation.valueOf(orientationString);
		ITraitViewAttributes atts = mapper.convertValue(map, ITraitViewAttributes.class);
		atts.setOrientation(orientation);
		return atts;
	}
	
	public ISubCategoryViewAttributes parseSubCategoryViewAttributes(Object object){
		return mapper.convertValue(object, ISubCategoryViewAttributes.class);
	}
	
	public ICategoryViewAttributes parseCategoryViewAttributes(Object object){
		return mapper.convertValue(object, ICategoryViewAttributes.class);
	}
	
	

}
