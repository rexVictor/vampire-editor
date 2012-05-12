package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import vampire.editor.domain.sheet.Category;
import vampire.editor.domain.sheet.Data;
import vampire.editor.domain.sheet.SubCategory;
import vampire.editor.domain.sheet.Trait;
import vampire.editor.domain.sheet.Value;
import vampire.editor.domain.sheet.view.TraitViewAttributes;
import vampire.editor.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.domain.sheet.view.TraitViewAttributesAPI;
import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributesAPI;
import vampire.editor.plugin.fullapi.sheet.ITrait;
import vampire.editor.plugin.fullapi.sheet.IValue;
import vampire.editor.plugin.fullapi.sheet.SheetConstructors;
import vampire.editor.sheetloader.domain.TraitAnnotated;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class TraitsParser {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private final SheetConstructors constructors;	
	
	public TraitsParser(SheetConstructors constructors) {
		super();
		this.constructors = constructors;
		SimpleModule module = new SimpleModule();
		module.setMixInAnnotation(Trait.class, TraitAnnotated.class);
		module.addAbstractTypeMapping(ITrait.class, constructors.getImplementingClassOf(ITrait.class));
		module.addAbstractTypeMapping(IValue.class, constructors.getImplementingClassOf(IValue.class));
		module.addAbstractTypeMapping(ValueViewAttributesAPI.class, ValueViewAttributes.class);
		module.addAbstractTypeMapping(TraitViewAttributesAPI.class, TraitViewAttributes.class);
		
		mapper.registerModule(module);
	}

	public IValue getValue(Object object){
		try{
			String string = mapper.writeValueAsString(object);
			
			IValue value = mapper.readValue(string, IValue.class);
			System.out.println(value.getMinValue());
			System.out.println(value.getMaxValue());
			return value;
		}
		catch (IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ITrait getTrait(Object object){
		Map<?, ?> map = (Map<?, ?>) object;
		ITrait trait = mapper.convertValue(object, Trait.class);
		return trait;		
	}
	
	public SubCategory getSubCategory(Object object){
		Map<?,?> subCatMap = (Map<?, ?>) object;
		List<?> traits = (List<?>) subCatMap.get("traits");
		SubCategory subCat = new SubCategory(null);
		subCat.setName((String) subCatMap.get("name")); 
		for (Object o : traits){
			subCat.add(getTrait(o));
		}
		return subCat;
	}
	
	public Category getCategory(Object object){
		Map<?, ?> catMap = (Map<?, ?>) object;
		Category cat = new Category(null);
		cat.setName((String) catMap.get("name"));
		List<?> list= (List<?>) catMap.get("subCats");
		for (Object o : list){
			cat.add(getSubCategory(o));
		}
		return cat;
	}
	
	public Data<Category> getCategories(Object object){
		List<?> categories = (List<?>) object;
		Data<Category> data = new Data<>(null);
		for (Object o : categories){
			data.add(getCategory(o));
		}
		return data;
	}
	
	

}
