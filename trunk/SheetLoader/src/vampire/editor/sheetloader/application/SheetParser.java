package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.plugin.api.plugin.ManagerAPI;
import vampire.editor.plugin.fullapi.sheet.ICategory;
import vampire.editor.plugin.fullapi.sheet.IData;
import vampire.editor.plugin.fullapi.sheet.IMetaEntry;
import vampire.editor.plugin.fullapi.sheet.ISheet;
import vampire.editor.plugin.fullapi.sheet.ISubCategory;
import vampire.editor.plugin.fullapi.sheet.ITrait;
import vampire.editor.plugin.fullapi.sheet.IValue;
import vampire.editor.plugin.fullapi.sheet.SheetConstructors;
import vampire.editor.plugin.fullapi.sheet.view.ICategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.IMetaEntryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ITraitViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;

public class SheetParser {
	
	private final ValueViewAttributesHolder valueViewAttsHolder;
	
	private final TraitViewAttributesHolder traitViewAttsHolder;
	
	private final SubCategoryViewAttributesHolder subCategoryViewAttsHolder;
	
	private final FontHolder fontHolder;
	
	private final SheetConstructors constructors;
	
	private final ISheet sheet;
	
	private final ValueHolder valueHolder;
	
	private final CategoryViewAttributesHolder categoryViewAttsHolder;
	
	private final MetaViewEntryAttributesHolder metaViewEntryAttributesHolder;
	
	public SheetParser(Path path, SheetConstructors constructors, ManagerAPI manager) throws JsonParseException, JsonMappingException, IOException, InstantiationException, IllegalAccessException {
		super();
		this.constructors = constructors;
		valueViewAttsHolder = new ValueViewAttributesHolder(path.resolve("valueviewatts.json"), constructors.getImplementingClassOf(IValueViewAttributes.class));
		fontHolder = new FontHolder(path.resolve("fonts.json"), manager);
		traitViewAttsHolder = new TraitViewAttributesHolder(path.resolve("traitviewatts.json"), constructors.getImplementingClassOf(ITraitViewAttributes.class), fontHolder);
		subCategoryViewAttsHolder = new SubCategoryViewAttributesHolder(path.resolve("subcategoryviewatts.json"), 
				constructors.getImplementingClassOf(ISubCategoryViewAttributes.class), fontHolder);
		sheet = constructors.getImplementingClassOf(ISheet.class).newInstance();
		valueHolder = new ValueHolder(path.resolve("values.json"), 
				constructors.getImplementingClassOf(IValue.class));
		categoryViewAttsHolder = new CategoryViewAttributesHolder(constructors.getImplementingClassOf(ICategoryViewAttributes.class), path.resolve("categoryviewatts.json"));
		ObjectMapper mapper = new ObjectMapper();
		Path sheet = path.resolve("sheet.json");
		@SuppressWarnings("unchecked")
		Map<String, ?> protoSheet = mapper.readValue(sheet.toFile(), Map.class);
		metaViewEntryAttributesHolder = new MetaViewEntryAttributesHolder(path.resolve("metaentryviewatts.json"), 
				constructors.getImplementingClassOf(IMetaEntryViewAttributes.class), fontHolder);
		parse(protoSheet);
	}



	@SuppressWarnings("unchecked")
	private void parse(Map<String, ?> protoSheet) throws InstantiationException, IllegalAccessException{
		IData<IMetaEntry> metas = constructors.getImplementingClassOf(IData.class).newInstance();
		parseMetas((List<Map<String, ?>>) protoSheet.get("meta"), metas);
		IData<ICategory> categories = constructors.getImplementingClassOf(IData.class).newInstance();
		parseCategories((List<Map<String, ?>>) protoSheet.get("traits"), categories);
		sheet.setCategories(categories);
		sheet.setMeta(metas);
	}
	
	private void parseMetas(List<Map<String, ?>> protoMetas, IData<IMetaEntry> metas){
		for (Map<String, ?> protoMeta : protoMetas){
			metas.add(parseMeta(protoMeta));
		}
	}
	
	private IMetaEntry parseMeta(Map<String, ?> protoMeta){
		int viewID = (Integer) protoMeta.remove("viewAtts");
		ObjectMapper mapper = new ObjectMapper();
		IMetaEntry metaEntry = mapper.convertValue(protoMeta, constructors.getImplementingClassOf(IMetaEntry.class));
		metaEntry.setViewAtts(metaViewEntryAttributesHolder.getMetaEntryViewAttributesByID(viewID));
		return metaEntry;
	}
	
	private void parseCategories(List<Map<String, ?>> protoCategories, IData<ICategory> categories) throws InstantiationException, IllegalAccessException{
		for (Map<String, ?> protoCategory : protoCategories){
			categories.add(parseCategory(protoCategory));
		}
	}
	
	@SuppressWarnings("unchecked")
	private ICategory parseCategory(Map<String, ?> protoCategory) throws InstantiationException, IllegalAccessException{
		ICategory category = constructors.getImplementingClassOf(ICategory.class).newInstance();
		parseSubCategories((List<Map<String, ?>>) protoCategory.get("subCats"), category);
		int viewID = (Integer) protoCategory.get("viewAtts");
		category.setViewAtts(categoryViewAttsHolder.getCategoryViewAttributesByID(viewID));
		category.setName((String) protoCategory.get("name"));
		return category;
	}
	
	private void parseSubCategories(List<Map<String, ?>> protoSubCategories, ICategory category) throws InstantiationException, IllegalAccessException{
		for (Map<String, ?> protoSubCategory : protoSubCategories){
			category.add(parseSubCategory(protoSubCategory));
		}
	}
	
	@SuppressWarnings("unchecked")
	private ISubCategory parseSubCategory(Map<String, ?> protoSubCategory) throws InstantiationException, IllegalAccessException{
		ISubCategory subCat = constructors.getImplementingClassOf(ISubCategory.class).newInstance();
		parseTraits((List<Map<String, ?>>) protoSubCategory.get("traits"), subCat);
		subCat.setName((String) protoSubCategory.get("name"));
		int viewId = (Integer) protoSubCategory.get("viewAtts");
		subCat.setViewAtts(subCategoryViewAttsHolder.getSubCategoryViewAttributesByID(viewId));
		return subCat;
	}
	
	private void parseTraits(List<Map<String, ?>> protoTraits, ISubCategory subCat) throws InstantiationException, IllegalAccessException{
		for (Map<String, ?> protoTrait : protoTraits){
			subCat.add(parseTrait(protoTrait));
		}		
	}
	
	@SuppressWarnings("unchecked")
	private ITrait parseTrait(Map<String, ?> protoTrait) throws InstantiationException, IllegalAccessException{
		ITrait trait = constructors.getImplementingClassOf(ITrait.class).newInstance();
		trait.setValue(parseValue((Map<String, Integer>) protoTrait.get("value")));
		int viewAttsID = (Integer) protoTrait.get("viewAtts");
		trait.setViewAtts(traitViewAttsHolder.getTraitViewAttributesByID(viewAttsID));
		trait.setName((String) protoTrait.get("name"));
		return trait;
	}
	
	private IValue parseValue(Map<String, Integer> protoValue){
		IValue value = valueHolder.getValueByID(protoValue.get("id"));
		int viewID = protoValue.get("viewAtts");
		IValueViewAttributes viewAtts = valueViewAttsHolder.getValueViewAttributesById(viewID);
		value.setViewAtts(viewAtts);
		return value;
	}
	
	public ISheet getSheet(){
		return sheet;
	}

}
