package vampire.editor.sheetloader.application;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.domain.sheet.view.TraitViewAttributes;
import vampire.editor.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.Orientation;


public class ViewParser {
	
	
	private final ObjectMapper mapper = new ObjectMapper();

	public ViewParser() {
		super();
		
	}
	
	public ValueViewAttributes parseValueViewAttributes(Object object){
		return mapper.convertValue(object, ValueViewAttributes.class);
	}
	
	public TraitViewAttributes parseTraitViewAttributes(Object object){
		@SuppressWarnings("unchecked")
		Map<String, ?> map = (Map<String, ?>) object;
		String orientationString = (String) map.remove("orientation");
		Orientation orientation = Orientation.valueOf(orientationString);
		TraitViewAttributes atts = mapper.convertValue(map, TraitViewAttributes.class);
		atts.setOrientation(orientation);
		return atts;
	}
	
	public SubCategoryViewAttributes parseSubCategoryViewAttributes(Object object){
		return mapper.convertValue(object, SubCategoryViewAttributes.class);
	}
	
	public CategoryViewAttributes parseCategoryViewAttributes(Object object){
		return mapper.convertValue(object, CategoryViewAttributes.class);
	}
	
	

}
