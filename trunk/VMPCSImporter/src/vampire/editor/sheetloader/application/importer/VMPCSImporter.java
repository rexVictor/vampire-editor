package vampire.editor.sheetloader.application.importer;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.domain.sheet.*;
import vampire.editor.domain.sheet.view.*;

import vampire.editor.plugin.api.domain.ResourcesHolderAPI;

import vampire.editor.sheetloader.common.ClassToFileMapper;
import vampire.editor.sheetloader.common.ModelToViewMap;

public class VMPCSImporter {
	
	private final Path root;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private final Objects<Font> fonts;
	
	private final Objects<Value> values;
	
	private final Objects<SubCategoryViewAttributes> subCatViewAtts;
	
	private final Objects<CategoryViewAttributes> catViewAtts;
	
	private final Objects<MetaEntryViewAttributes> metaEntryViewAttributes;
	
	private final Objects<TraitViewAttributes> traitViewAtts;
	
	private final Objects<ValueViewAttributes> valueViewAtts;
	
	private final ModelToViewMap modelToViewMap;
	
	private final ModelToViewModelMapper viewModelMapper = new ModelToViewModelMapper();
	
	public VMPCSImporter(ResourcesHolderAPI resources, Path path) throws VMPCSImportException{
		super();
		this.root = path;
		try {
			// Loads all "leaves".
			fonts = new Objects<>(root, Font.class, resources, null);
			catViewAtts = new Objects<>(root, CategoryViewAttributes.class, resources, fonts);
			metaEntryViewAttributes = new Objects<>(root, MetaEntryViewAttributes.class, resources, fonts);
			subCatViewAtts = new Objects<>(root, SubCategoryViewAttributes.class, resources, fonts);
			values = new Objects<>(root, Value.class, resources, null);
			traitViewAtts = new Objects<>(root, TraitViewAttributes.class, resources, fonts);
			valueViewAtts = new Objects<>(root, ValueViewAttributes.class, resources, fonts);
			// Loads the ModelToViewProtoMap
			modelToViewMap = ModelToViewMap.loadModelToViewMap(root.resolve("modeltoviewmap.json"));
		} catch (JsonParseException | JsonMappingException | ClassCastException | NullPointerException e) {
			//illegal json format
			throw new VMPCSImportException(e);
		} catch (IOException e){
			throw new VMPCSImportException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public VampireDocument load() throws VMPCSImportException{
		try{
			// Loads the Sheet
			Path sheetFile = root.resolve(ClassToFileMapper.paths.get(Sheet.class));
			Sheet sheet = new Sheet();
			Map<String, Object> protoSheet = mapper.readValue(sheetFile.toFile(), Map.class);
			Data<MetaEntry> meta = loadMeta((List<Map<String, Object>>) protoSheet.get("meta"));
			sheet.setMeta(meta);
			Data<Category> categories = loadCategories((List<Map<String, Object>>) protoSheet.get("traits"));
			sheet.setCategories(categories);
			return new VampireDocument(sheet, viewModelMapper);
		}
		//wrong JSON Format
		catch(JsonParseException | JsonMappingException | ClassCastException e){
			throw new VMPCSImportException(e);
		}
		//general io error
		catch (IOException e) {
			throw new VMPCSImportException(e);
		}
	}
	
	private Data<MetaEntry> loadMeta(List<Map<String, Object>> protoMeta){
		Data<MetaEntry> meta = new Data<>();
		for (Map<String, Object> protoMetaEntry : protoMeta){
			meta.add(loadMetaEntry(protoMetaEntry));
		}
		return meta;
	}
	
	private MetaEntry loadMetaEntry(Map<String, Object> protoMetaEntry){
		int id = (int) protoMetaEntry.remove("mapid");
		ObjectMapper mapper = new ObjectMapper();
		MetaEntry entry = mapper.convertValue(protoMetaEntry, MetaEntry.class);
		MetaEntryViewAttributes viewAtts = metaEntryViewAttributes.getObjectByID(modelToViewMap.getMetaView(id));
		viewModelMapper.putView(entry, viewAtts);
		return entry;
	}
	
	private Data<Category> loadCategories(List<Map<String, Object>> protoCategories){ 
		Data<Category> categories = new Data<>();
		for (Map<String, Object> protoCategory : protoCategories){
			categories.add(loadCategory(protoCategory));
		}
		return categories;
	}
	
	private Category loadCategory(Map<String, Object> protoCategory){
		String name = (String) protoCategory.remove("name");
		int id = (int) protoCategory.remove("mapid");
		CategoryViewAttributes viewAtts = catViewAtts.getObjectByID(modelToViewMap.getCategoryView(id));
		Category category = new Category();
		category.setName(name);
		viewModelMapper.putView(category, viewAtts);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> subCats = (List<Map<String, Object>>) protoCategory.remove("subCats");
		for (Map<String, Object> protoSubCat : subCats){
			category.add(loadSubCategory(protoSubCat));
		}		
		
		return category;
	}
	
	private SubCategory loadSubCategory(Map<String, Object> protoSubCategory){
		String name = (String) protoSubCategory.remove("name");
		int id = (int) protoSubCategory.remove("mapid");
		SubCategoryViewAttributes viewAtts = subCatViewAtts.getObjectByID(modelToViewMap.getSubcategoryView(id));
		SubCategory subCategory = new SubCategory();
		subCategory.setName(name);
		viewModelMapper.putView(subCategory, viewAtts);
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> traits = (List<Map<String, Object>>) protoSubCategory.remove("traits");
		for (Map<String, Object> protoTrait : traits){
			subCategory.add(loadTrait(protoTrait));
		}		
		return subCategory;
	}
	
	private Trait loadTrait(Map<String, Object> protoTrait){
		@SuppressWarnings("unchecked")
		Map<String, Object> protoValue = (Map<String, Object>) protoTrait.remove("value");
		int id = (int) protoTrait.remove("mapid");
		TraitViewAttributes viewAtts = traitViewAtts.getObjectByID(modelToViewMap.getTraitView(id));
		Value value = loadValue(protoValue);
		String name = (String) protoTrait.remove("name");
		Trait trait = new Trait(name, value);
		viewModelMapper.putView(trait, viewAtts);
		return trait;
	}
	
	private Value loadValue(Map<String, Object> protoValue){
		int id = (int) protoValue.remove("id");
		int mapid = (int) protoValue.remove("mapid");
		ValueViewAttributes viewAtts = valueViewAtts.getObjectByID(modelToViewMap.getValueView(mapid));
		Value value = values.getObjectByID(id);
		viewModelMapper.putView(value, viewAtts);
		return value;
	}
}