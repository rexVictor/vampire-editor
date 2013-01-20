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
import vampire.editor.domain.sheet.ModelToViewModelMapper;
import vampire.editor.domain.sheet.Sheet;
import vampire.editor.domain.sheet.SubCategory;
import vampire.editor.domain.sheet.Trait;
import vampire.editor.domain.sheet.Value;
import vampire.editor.domain.sheet.VampireDocument;
import vampire.editor.domain.sheet.view.CategoryViewAttributes;
import vampire.editor.domain.sheet.view.MetaEntryViewAttributes;
import vampire.editor.domain.sheet.view.SubCategoryViewAttributes;
import vampire.editor.domain.sheet.view.TraitViewAttributes;
import vampire.editor.domain.sheet.view.ValueViewAttributes;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;

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
	
	private Objects<SubCategoryViewAttributes> subCatViewAtts;
	
	private Objects<CategoryViewAttributes> catViewAtts;
	
	private Objects<MetaEntryViewAttributes> metaEntryViewAttributes;
	
	private Objects<TraitViewAttributes> traitViewAtts;
	
	private Objects<ValueViewAttributes> valueViewAtts;
	
	public VMPCSImporter(ResourcesHolderAPI resources) {
		super();
		this.resources = resources;
	}



	@SuppressWarnings("unchecked")
	public VampireDocument load(Path root) throws VMPCSImportException{
		try{
			initializeLoading(root);
			Path sheetFile = root.resolve(paths.get(Sheet.class));
			Sheet sheet = new Sheet();
			ModelToViewModelMapper viewModelMapper = new ModelToViewModelMapper();
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> protoSheet = mapper.readValue(sheetFile.toFile(), Map.class);
			Data<MetaEntry> meta = loadMeta((List<Map<String, Object>>) protoSheet.get("meta"), viewModelMapper);
			sheet.setMeta(meta);
			Data<Category> categories = loadCategories((List<Map<String, Object>>) protoSheet.get("traits"), viewModelMapper);
			sheet.setCategories(categories);
			
			return new VampireDocument(sheet, viewModelMapper);
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
		values = new Objects<>(root, Value.class, resources);
		traitViewAtts = new Objects<>(root, TraitViewAttributes.class, resources);
		valueViewAtts = new Objects<>(root, ValueViewAttributes.class, resources);
	}
	
	private void loadingFinished(){
		this.catViewAtts = null;
		this.metaEntryViewAttributes = null;
		this.root = null;
		this.values = null;
	}
	
	private Data<MetaEntry> loadMeta(List<Map<String, Object>> protoMeta, ModelToViewModelMapper mapper){
		Data<MetaEntry> meta = new Data<>();
		for (Map<String, Object> protoMetaEntry : protoMeta){
			meta.add(loadMetaEntry(protoMetaEntry, mapper));
		}
		return meta;
	}
	
	private MetaEntry loadMetaEntry(Map<String, Object> protoMetaEntry, ModelToViewModelMapper viewmapper){
		int viewAttId = (int) protoMetaEntry.remove("viewAtts");
		ObjectMapper mapper = new ObjectMapper();
		MetaEntry entry = mapper.convertValue(protoMetaEntry, MetaEntry.class);
		MetaEntryViewAttributes viewAtts = metaEntryViewAttributes.getObjectByID(viewAttId);
		viewmapper.putView(entry, viewAtts);
		return entry;
	}
	
	private Data<Category> loadCategories(List<Map<String, Object>> protoCategories, ModelToViewModelMapper viewMapper) 
				throws ClassCastException, NullPointerException{
		Data<Category> categories = new Data<>();
		for (Map<String, Object> protoCategory : protoCategories){
			categories.add(loadCategory(protoCategory, viewMapper));
		}
		return categories;
	}
	
	private Category loadCategory(Map<String, Object> protoCategory, ModelToViewModelMapper viewMapper)
				throws ClassCastException, NullPointerException{
		String name = (String) protoCategory.get("name");
		int viewAttId = (int) protoCategory.get("viewAtts");
		CategoryViewAttributes viewAtts = catViewAtts.getObjectByID(viewAttId);
		Category category = new Category();
		category.setName(name);
		viewMapper.putView(category, viewAtts);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> subCats = (List<Map<String, Object>>) protoCategory.get("subCats");
		for (Map<String, Object> protoSubCat : subCats){
			category.add(loadSubCategory(protoSubCat, viewMapper));
		}		
		
		return category;
	}
	
	private SubCategory loadSubCategory(Map<String, Object> protoSubCategory, ModelToViewModelMapper viewMapper)
				throws ClassCastException, NullPointerException{
		String name = (String) protoSubCategory.get("name");
		int viewAttId = (int) protoSubCategory.get("viewAtts");
		SubCategoryViewAttributes viewAtts = subCatViewAtts.getObjectByID(viewAttId);
		SubCategory subCategory = new SubCategory();
		subCategory.setName(name);
		viewMapper.putView(subCategory, viewAtts);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> traits = (List<Map<String, Object>>) protoSubCategory.get("traits");
		for (Map<String, Object> protoTrait : traits){
			subCategory.add(loadTrait(protoTrait, viewMapper));
		}		
		return subCategory;
	}
	
	private Trait loadTrait(Map<String, Object> protoTrait, ModelToViewModelMapper viewMapper)
				throws ClassCastException, NullPointerException{
		@SuppressWarnings("unchecked")
		Map<String, Object> protoValue = (Map<String, Object>) protoTrait.get("value");
		int viewAttId = (int) protoTrait.get("viewAtts");
		TraitViewAttributes viewAtts = traitViewAtts.getObjectByID(viewAttId);
		Value value = loadValue(protoValue, viewMapper);
		String name = (String) protoTrait.get("name");
		Trait trait = new Trait(name, value);
		viewMapper.putView(trait, viewAtts);
		return trait;
	}
	
	private Value loadValue(Map<String, Object> protoValue, ModelToViewModelMapper viewMapper)
				throws ClassCastException, NullPointerException{
		int id = (int) protoValue.get("id");
		int viewId = (int) protoValue.get("viewAtts");
		ValueViewAttributes viewAtts = valueViewAtts.getObjectByID(viewId);
		Value value = values.getObjectByID(id);
		viewMapper.putView(value, viewAtts);
		
		return value;
		
	}
	

}
