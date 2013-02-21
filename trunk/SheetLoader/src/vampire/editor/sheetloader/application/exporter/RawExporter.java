package vampire.editor.sheetloader.application.exporter;

import java.util.HashMap;
import java.util.Map;

import vampire.editor.domain.sheet.*;
import vampire.editor.domain.sheet.view.*;

import vampire.editor.plugin.api.domain.sheet.Nameable;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class RawExporter {
	
	private abstract class InnerExporter{
		
		protected abstract Map<String, Object> export(Object object);
	}
	
	private class UsualExporter extends InnerExporter{
		
		@Override
		public Map<String, Object> export(Object object){
			Map<String, Object> map = mapper.convertValue(object, typeReferenceJsonMap);
			map.remove("font");
			return map;
		}
	}
	
	private class MetaEntryViewAttributisExporter extends InnerExporter{
		
		@Override
		public Map<String, Object> export(Object object){
			Map<String, Object> map = mapper.convertValue(object, typeReferenceJsonMap);
			map.remove("titleFont");
			map.remove("contentFont");
			return map;
		}
	}
	
	private class DataExporter extends InnerExporter{

		@Override
		protected Map<String, Object> export(Object object) {
			Nameable nameable =  (Nameable) object;
			Map<String, Object> map = new HashMap<>();
			map.put("name", nameable.getName());
			return map;
		}
		
	}
	
	private final TypeReference<Map<String, Object>> typeReferenceJsonMap = new TypeReference<Map<String,Object>>(){};
	
	private final Map<Class<?>, InnerExporter> exporters = new HashMap<>();
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	public RawExporter(){
		InnerExporter usual = new UsualExporter();
		DataExporter dataExporter = new DataExporter();
		exporters.put(ValueViewAttributes.class, usual);
		exporters.put(TraitViewAttributes.class, usual);
		exporters.put(SubCategoryViewAttributes.class, usual);
		exporters.put(CategoryViewAttributes.class, usual);
		exporters.put(Value.class, usual);
		exporters.put(MetaEntryViewAttributes.class, new MetaEntryViewAttributisExporter());
		exporters.put(MetaEntry.class, usual);
		exporters.put(Category.class, dataExporter);
		exporters.put(SubCategory.class, dataExporter);
		exporters.put(Trait.class, dataExporter);
		
		
		SimpleModule module = new SimpleModule();
		module.addSerializer(new FontEmptySerializer());
		mapper.registerModule(module);
	}

	
	public Map<String, Object> export(Object object){
		return exporters.get(object.getClass()).export(object);
	}
}
