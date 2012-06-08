package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.domain.sheet.Category;
import vampire.editor.domain.sheet.Data;
import vampire.editor.domain.sheet.MetaEntry;
import vampire.editor.domain.sheet.Sheet;
import vampire.editor.domain.sheet.SubCategory;
import vampire.editor.domain.sheet.Trait;
import vampire.editor.domain.sheet.Value;
import vampire.editor.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.plugin.ManagerAPI;

public class SheetParser {
	
	private final ValueViewAttributesHolder valueViewAttsHolder;
	
	private final TraitViewAttributesHolder traitViewAttsHolder;
	
	private final SubCategoryViewAttributesHolder subCategoryViewAttsHolder;
	
	private final FontHolder fontHolder;
	
	private final Sheet sheet;
	
	private final ValueHolder valueHolder;
	
	private final CategoryViewAttributesHolder categoryViewAttsHolder;
	
	private final MetaViewEntryAttributesHolder metaViewEntryAttributesHolder;
	
	public SheetParser(Path path,  ManagerAPI manager) throws JsonParseException, JsonMappingException, IOException, InstantiationException, IllegalAccessException {
		super();
		valueViewAttsHolder = new ValueViewAttributesHolder(path.resolve("valueviewatts.json"));
		fontHolder = new FontHolder(path.resolve("fonts.json"), manager.getResourcesHolder());
		traitViewAttsHolder = new TraitViewAttributesHolder(path.resolve("traitviewatts.json"), fontHolder);
		subCategoryViewAttsHolder = new SubCategoryViewAttributesHolder(path.resolve("subcategoryviewatts.json"), 
				fontHolder);
		sheet = Sheet.class.newInstance();
		valueHolder = new ValueHolder(path.resolve("values.json"));
		categoryViewAttsHolder = new CategoryViewAttributesHolder(path.resolve("categoryviewatts.json"));
		ObjectMapper mapper = new ObjectMapper();
		Path sheet = path.resolve("sheet.json");
		@SuppressWarnings("unchecked")
		Map<String, ?> protoSheet = mapper.readValue(sheet.toFile(), Map.class);
		metaViewEntryAttributesHolder = new MetaViewEntryAttributesHolder(path.resolve("metaentryviewatts.json"),  fontHolder);
		parse(protoSheet);
	}



	@SuppressWarnings("unchecked")
	private void parse(Map<String, ?> protoSheet) throws InstantiationException, IllegalAccessException{
		Data<MetaEntry> metas = Data.class.newInstance();
		parseMetas((List<Map<String, ?>>) protoSheet.get("meta"), metas);
		Data<Category> categories = Data.class.newInstance();
		parseCategories((List<Map<String, ?>>) protoSheet.get("traits"), categories);
		sheet.setCategories(categories);
		sheet.setMeta(metas);
	}
	
	private void parseMetas(List<Map<String, ?>> protoMetas, Data<MetaEntry> metas){
		for (Map<String, ?> protoMeta : protoMetas){
			metas.add(parseMeta(protoMeta));
		}
	}
	
	private MetaEntry parseMeta(Map<String, ?> protoMeta){
		int viewID = (Integer) protoMeta.remove("viewAtts");
		ObjectMapper mapper = new ObjectMapper();
		MetaEntry metaEntry = mapper.convertValue(protoMeta, MetaEntry.class);
		metaEntry.setViewAtts(metaViewEntryAttributesHolder.getMetaEntryViewAttributesByID(viewID));
		return metaEntry;
	}
	
	private void parseCategories(List<Map<String, ?>> protoCategories, Data<Category> categories) throws InstantiationException, IllegalAccessException{
		for (Map<String, ?> protoCategory : protoCategories){
			categories.add(parseCategory(protoCategory));
		}
	}
	
	@SuppressWarnings("unchecked")
	private Category parseCategory(Map<String, ?> protoCategory) throws InstantiationException, IllegalAccessException{
		Category category = Category.class.newInstance();
		parseSubCategories((List<Map<String, ?>>) protoCategory.get("subCats"), category);
		int viewID = (Integer) protoCategory.get("viewAtts");
		category.setViewAtts(categoryViewAttsHolder.getCategoryViewAttributesByID(viewID));
		category.setName((String) protoCategory.get("name"));
		return category;
	}
	
	private void parseSubCategories(List<Map<String, ?>> protoSubCategories, Category category) throws InstantiationException, IllegalAccessException{
		for (Map<String, ?> protoSubCategory : protoSubCategories){
			category.add(parseSubCategory(protoSubCategory));
		}
	}
	
	@SuppressWarnings("unchecked")
	private SubCategory parseSubCategory(Map<String, ?> protoSubCategory) throws InstantiationException, IllegalAccessException{
		SubCategory subCat = SubCategory.class.newInstance();
		parseTraits((List<Map<String, ?>>) protoSubCategory.get("traits"), subCat);
		subCat.setName((String) protoSubCategory.get("name"));
		int viewId = (Integer) protoSubCategory.get("viewAtts");
		subCat.setViewAtts(subCategoryViewAttsHolder.getSubCategoryViewAttributesByID(viewId));
		return subCat;
	}
	
	private void parseTraits(List<Map<String, ?>> protoTraits, SubCategory subCat) throws InstantiationException, IllegalAccessException{
		for (Map<String, ?> protoTrait : protoTraits){
			subCat.add(parseTrait(protoTrait));
		}		
	}
	
	@SuppressWarnings("unchecked")
	private Trait parseTrait(Map<String, ?> protoTrait) throws InstantiationException, IllegalAccessException{
		Trait trait = Trait.class.newInstance();
		trait.setValue(parseValue((Map<String, Integer>) protoTrait.get("value")));
		int viewAttsID = (Integer) protoTrait.get("viewAtts");
		trait.setViewAtts(traitViewAttsHolder.getTraitViewAttributesByID(viewAttsID));
		trait.setName((String) protoTrait.get("name"));
		return trait;
	}
	
	private Value parseValue(Map<String, Integer> protoValue){
		Value value = valueHolder.getValueByID(protoValue.get("id"));
		int viewID = protoValue.get("viewAtts");
		ValueViewAttributes viewAtts = valueViewAttsHolder.getValueViewAttributesById(viewID);
		value.setViewAtts(viewAtts);
		return value;
	}
	
	public Sheet getSheet(){
		return sheet;
	}

}
