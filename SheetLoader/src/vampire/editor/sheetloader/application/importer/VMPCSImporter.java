package vampire.editor.sheetloader.application.importer;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
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
import vampire.editor.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.domain.sheet.view.MetaEntryViewAttributes;
import vampire.editor.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.domain.sheet.view.TraitViewAttributes;
import vampire.editor.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.plugin.ResourcesHolderAPI;

public class VMPCSImporter {
	
	static
	{
		Path fonts = Paths.get("fonts.json");
		Path valueViewAtts = Paths.get("valueviewatts.json");
		Path traitViewAtts = Paths.get("traitviewatts.json");
		Path subCategoryViewAtts = Paths.get("subcategoryviewatts.json");
		Path categroyViewAtts = Paths.get("categoryviewatts.json");
		Path metaEntryViewAtts = Paths.get("metaentryviewatts.json");
		Path sheet = Paths.get("sheet.json");
		Path values = Paths.get("values.json");
		Map<Class<?>, Path> map = new HashMap<>();
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
	
	public static final Map<Class<?>, Path> paths;

	private final ResourcesHolderAPI resources;
	
	private Path root = null;
	
	private Objects<Value> values;
	
	private Objects<ValueViewAttributes> valueViewAtts;
	
	private Objects<TraitViewAttributes> traitViewAtts;
	
	private Objects<SubCategoryViewAttributes> subCatViewAtts;
	
	private Objects<CategoryViewAttributes> catViewAtts;
	
	private Objects<MetaEntryViewAttributes> metaEntryViewAttributes;
	
	public VMPCSImporter(ResourcesHolderAPI resources) {
		super();
		this.resources = resources;
	}



	@SuppressWarnings("unchecked")
	public Sheet load(Path root) throws VMPCSImportException{
		try{
			initializeLoading(root);
			Path sheetFile = root.resolve(paths.get(Sheet.class));
			Sheet sheet = new Sheet();
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> protoSheet = mapper.readValue(sheetFile.toFile(), Map.class);
			Data<MetaEntry> meta = loadMeta((List<Map<String, Object>>) protoSheet.get("meta"));
			sheet.setMeta(meta);
			Data<Category> categories = loadCategories((List<Map<String, Object>>) protoSheet.get("traits"));
			sheet.setCategories(categories);
			return sheet;
		} catch (ClassCastException | NullPointerException | IOException e) {
			throw new VMPCSImportException(e);
		}
		finally{
			loadingFinished();
		}
		
			
	}
	
	private void initializeLoading(Path path) throws JsonParseException, JsonMappingException, IOException{
		this.root = path;
		catViewAtts = new Objects<>(root, CategoryViewAttributes.class, resources);
		metaEntryViewAttributes = new Objects<>(root, MetaEntryViewAttributes.class, resources);
		subCatViewAtts = new Objects<>(root, SubCategoryViewAttributes.class, resources);
		traitViewAtts = new Objects<>(root, TraitViewAttributes.class, resources);
		values = new Objects<>(root, Value.class, resources);
		valueViewAtts = new Objects<>(root, ValueViewAttributes.class, resources);
	}
	
	private void loadingFinished(){
		this.catViewAtts = null;
		this.metaEntryViewAttributes = null;
		this.root = null;
		this.subCatViewAtts = null;
		this.traitViewAtts = null;
		this.values = null;
		this.valueViewAtts = null;	
	}
	
	private Data<MetaEntry> loadMeta(List<Map<String, Object>> protoMeta){
		Data<MetaEntry> meta = new Data<>();
		for (Map<String, Object> protoMetaEntry : protoMeta){
			meta.add(loadMetaEntry(protoMetaEntry));
		}
		return meta;
	}
	
	private MetaEntry loadMetaEntry(Map<String, Object> protoMetaEntry){
		int viewAttId = (int) protoMetaEntry.remove("viewAtts");
		ObjectMapper mapper = new ObjectMapper();
		MetaEntry entry = mapper.convertValue(protoMetaEntry, MetaEntry.class);
		MetaEntryViewAttributes viewAtts = metaEntryViewAttributes.getObjectByID(viewAttId);
		entry.setViewAtts(viewAtts);
		return entry;
		
		
	}
	
	private Data<Category> loadCategories(List<Map<String, Object>> protoCategories) throws ClassCastException, NullPointerException{
		Data<Category> categories = new Data<>();
		for (Map<String, Object> protoCategory : protoCategories){
			categories.add(loadCategory(protoCategory));
		}
		
		return categories;
		
	}
	
	private Category loadCategory(Map<String, Object> protoCategory) throws ClassCastException, NullPointerException{
		String name = (String) protoCategory.get("name");
		int viewAttId = (int) protoCategory.get("viewAtts");
		CategoryViewAttributes viewAtts = catViewAtts.getObjectByID(viewAttId);
		Category category = new Category();
		category.setName(name);
		category.setViewAtts(viewAtts);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> subCats = (List<Map<String, Object>>) protoCategory.get("subCats");
		for (Map<String, Object> protoSubCat : subCats){
			category.add(loadSubCategory(protoSubCat));
		}		
		
		return category;
	}
	
	private SubCategory loadSubCategory(Map<String, Object> protoSubCategory) throws ClassCastException, NullPointerException{
		String name = (String) protoSubCategory.get("name");
		int viewAttId = (int) protoSubCategory.get("viewAtts");
		SubCategoryViewAttributes viewAtts = subCatViewAtts.getObjectByID(viewAttId);
		SubCategory subCategory = new SubCategory();
		subCategory.setName(name);
		subCategory.setViewAtts(viewAtts);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> traits = (List<Map<String, Object>>) protoSubCategory.get("traits");
		for (Map<String, Object> protoTrait : traits){
			subCategory.add(loadTrait(protoTrait));
		}		
		return subCategory;
	}
	
	private Trait loadTrait(Map<String, Object> protoTrait) throws ClassCastException, NullPointerException{
		@SuppressWarnings("unchecked")
		Map<String, Object> protoValue = (Map<String, Object>) protoTrait.get("value");
		Value value = loadValue(protoValue);
		int viewAttId = (int) protoTrait.get("viewAtts");
		String name = (String) protoTrait.get("name");
		TraitViewAttributes viewAtts = traitViewAtts.getObjectByID(viewAttId);
		Trait trait = new Trait(name, value, viewAtts);
		return trait;
	}
	
	private Value loadValue(Map<String, Object> protoValue) throws ClassCastException, NullPointerException{
		int id = (int) protoValue.get("id");
		int viewAttId = (int) protoValue.get("viewAtts");
		Value value = values.getObjectByID(id);
		ValueViewAttributes viewAtts = valueViewAtts.getObjectByID(viewAttId);
		value.setViewAtts(viewAtts);
		return value;
		
	}
	

}
