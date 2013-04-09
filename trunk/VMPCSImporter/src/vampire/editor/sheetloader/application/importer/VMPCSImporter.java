package vampire.editor.sheetloader.application.importer;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
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

@Deprecated
public class VMPCSImporter {
	
	private static int id = 0;
	
	private static final Path temp = Paths.get(System.getProperty("java.io.tmpdir"));
	
	static{
		try {
			remove0(temp.resolve("vampireeditor"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private final Path root;
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private final Objects<Font> fonts;
	
	private final Objects<Value> values;
	
	private final Objects<SubCategoryViewAttributes> subCatViewAtts;
	
	private final Objects<CategoryViewAttributes> catViewAtts;
	
	private final Objects<MetaEntryViewAttributes> metaEntryViewAttributes;
	
	private final Objects<TraitViewAttributes> traitViewAtts;
	
	private final Objects<ValueViewAttributes> valueViewAtts;
	
	private final Objects<MeritViewAttributes> meritViewAtts;
	
	private final Objects<MeritEntryViewAttributes> meritEntryViewAtts;
	
	private final Objects<BloodPoolViewAttributes> bloodPoolViewAtts;
	
	private final Objects<HealthViewAttributes> healthViewAtts;
	
	private final Objects<HealthEntryViewAttributes> healthEntryViewAtts;
	
	private final Map<Class<?>, Objects<?>> allViewAtts = new HashMap<>();
	
	private final ModelToViewMap modelToViewMap;
	
	private final ModelToViewModelMapper viewModelMapper = new ModelToViewModelMapper();
	
	VMPCSImporter(ResourcesHolderAPI resources, Path path, boolean compressed) throws VMPCSImportException{
		super();
		if (compressed){
			try {
				this.root = uncompress(path);
			} catch (IOException e1) {
				try {
					remove();
				} catch (IOException e) {
					e.printStackTrace();
				}
				throw new VMPCSImportException(e1);
			}
		}
		else{
			this.root = path;
		}
		try {
			// Loads all "leaves".
			fonts = new Objects<>(root, Font.class, resources, null);
			catViewAtts = new Objects<>(root, CategoryViewAttributes.class, resources, fonts);
			metaEntryViewAttributes = new Objects<>(root, MetaEntryViewAttributes.class, resources, fonts);
			subCatViewAtts = new Objects<>(root, SubCategoryViewAttributes.class, resources, fonts);
			values = new Objects<>(root, Value.class, resources, null);
			traitViewAtts = new Objects<>(root, TraitViewAttributes.class, resources, fonts);
			valueViewAtts = new Objects<>(root, ValueViewAttributes.class, resources, fonts);
			bloodPoolViewAtts = new Objects<>(root, BloodPoolViewAttributes.class, resources, fonts);
			healthEntryViewAtts = new Objects<>(root, HealthEntryViewAttributes.class, resources, fonts);
			healthViewAtts = new Objects<>(root, HealthViewAttributes.class, resources, fonts);
			meritEntryViewAtts = new Objects<>(root, MeritEntryViewAttributes.class, resources, fonts);
			meritViewAtts	= new Objects<>(root, MeritViewAttributes.class, resources, fonts);
			// Loads the ModelToViewProtoMap
			modelToViewMap = ModelToViewMapFactory.loadModelToViewMap(root.resolve("modeltoviewmap.json"));
		} catch (JsonParseException | JsonMappingException | ClassCastException | NullPointerException e) {
			//illegal json format
			throw new VMPCSImportException(e);
		} catch (IOException e){
			try {
				remove();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			throw new VMPCSImportException(e);
		}
		allViewAtts.put(MeritViewAttributes.class, meritViewAtts);
		allViewAtts.put(MeritEntryViewAttributes.class, meritEntryViewAtts);
		allViewAtts.put(HealthViewAttributes.class, healthViewAtts);
		allViewAtts.put(HealthEntryViewAttributes.class, healthEntryViewAtts);
		allViewAtts.put(CategoryViewAttributes.class, catViewAtts);
		allViewAtts.put(SubCategoryViewAttributes.class, subCatViewAtts);
		allViewAtts.put(TraitViewAttributes.class, traitViewAtts);
		allViewAtts.put(ValueViewAttributes.class, valueViewAtts);
		allViewAtts.put(BloodPoolViewAttributes.class, bloodPoolViewAtts);
		allViewAtts.put(MetaEntryViewAttributes.class, metaEntryViewAttributes);
	}
	
	public VMPCSImporter(ResourcesHolderAPI resources, Path path) throws VMPCSImportException{
		this(resources, path, true);
	}
	
	private Path uncompress(Path path) throws IOException{
		id++;
		FileSystem fs = FileSystems.newFileSystem(path, null);
		Path root = fs.getPath("/");
		Path target = Files.createDirectories(temp.resolve("vampireeditor").resolve(id+""));
		DirectoryStream<Path> stream = Files.newDirectoryStream(root);
		for (Path path2 : stream){
			Files.copy(path2, target.resolve(path2.getFileName().toString()));
		}
		return target;
	}

	@SuppressWarnings("unchecked")
	public VampireDocument load() throws VMPCSImportException{
		VampireDocument document = null;
		try{
			// Loads the Sheet
			Path sheetFile = root.resolve(ClassToFileMapper.paths.get(Sheet.class));
			
			final Sheet sheet = new Sheet();
			Map<String, Object> protoSheet = mapper.readValue(sheetFile.toFile(), Map.class);
			
			Meta meta = loadMeta((List<Map<String, Object>>) protoSheet.get("meta"));
			sheet.setMeta(meta);
			
			Data<Category> categories = loadCategories((List<Map<String, Object>>) protoSheet.get("traits"));
			sheet.setCategories(categories);
			
			BloodPool bloodPool = loadBloodPool((Map<String, Object>) protoSheet.get("bloodpool"));
			sheet.setBloodPool(bloodPool);
			
			Health health = loadHealth((Map<String, Object>) protoSheet.remove("health"));
			sheet.setHealth(health);
			
			Merits merits = loadMerits((Map<String, Object>) protoSheet.remove("merits"));
			merits.setName("merits");
			sheet.setMerits(merits);
			
			Merits flaws = loadMerits((Map<String, Object>) protoSheet.remove("flaws"));
			flaws.setName("flaws");
			sheet.setFlaws(flaws);
			
			sheet.setBorderKey((String) protoSheet.remove("border"));
			
			document = new VampireDocument(sheet, viewModelMapper, null);
		}
		//wrong JSON Format
		catch(JsonParseException | JsonMappingException | ClassCastException e){
			throw new VMPCSImportException(e);
		}
		//general io error
		catch (IOException e) {
			throw new VMPCSImportException(e);
		}
		finally{
			try {
				remove();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return document;
	}
	
	private Merits loadMerits(Map<String, Object> protoMerits){
		int id = (int) protoMerits.remove("mapid");
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> entries = (List<Map<String, Object>>) protoMerits.remove("entries");
		Merits merits = new Merits();
		for (Map<String, Object> map : entries){
			merits.add(loadMerit(map));
		}
		MeritViewAttributes viewAtts = meritViewAtts.getObjectByID(modelToViewMap.getMeritsView(id));
		viewModelMapper.putView(merits, viewAtts);
		return merits;
	}
	
	private Merit loadMerit(Map<String, Object> protoMerit){
		return loadEntry(protoMerit, Merit.class, MeritEntryViewAttributes.class);
	}
	
	private Health loadHealth(Map<String, Object> protoHealth){
		int id = (int) protoHealth.remove("mapid");
		Health health = new Health();
		@SuppressWarnings("unchecked")
		List<Map<String, Object>> protoHealthEntries = (List<Map<String, Object>>) protoHealth.remove("levels");
		for (Map<String, Object> protoHealthEntry : protoHealthEntries){
			health.add(loadHealthEntry(protoHealthEntry));
		}
		HealthViewAttributes viewAtts = healthViewAtts.getObjectByID(modelToViewMap.getHealthView(id));
		viewModelMapper.putView(health, viewAtts);
		return health;
	}
	
	private <V, W> V loadEntry(Map<String, Object> protoEntry, Class<V> model, Class<W> view){
		int id = (int) protoEntry.remove("mapid");
		V entry = mapper.convertValue(protoEntry, model);
		@SuppressWarnings("unchecked")
		W viewAtts = (W) allViewAtts.get(view).getObjectByID(modelToViewMap.getView(model, id));
		viewModelMapper.putView(entry, viewAtts);
		return entry;
	}
	
	private HealthEntry loadHealthEntry(Map<String, Object> protoHeathEntry){
		return loadEntry(protoHeathEntry, HealthEntry.class, HealthEntryViewAttributes.class);
	}
	
	
	
	private BloodPool loadBloodPool(Map<String, Object> protoBloodPool){
		int id = (int) protoBloodPool.remove("mapid");
		BloodPool bloodPool = mapper.convertValue(protoBloodPool, BloodPool.class);
		BloodPoolViewAttributes viewAtts = bloodPoolViewAtts.getObjectByID(modelToViewMap.getBloodPoolView(id));
		viewModelMapper.putView(bloodPool, viewAtts);
		return bloodPool;
	}
	
	private Meta loadMeta(List<Map<String, Object>> protoMeta){
		Meta meta = new Meta();
		for (Map<String, Object> protoMetaEntry : protoMeta){
			meta.add(loadMetaEntry(protoMetaEntry));
		}
		return meta;
	}
	
	private MetaEntry loadMetaEntry(Map<String, Object> protoMetaEntry){
		return loadEntry(protoMetaEntry, MetaEntry.class, MetaEntryViewAttributes.class);
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
	
	private void remove() throws IOException{
		Path path = temp.resolve("vampireeditor").resolve(id+"");
		remove0(path);
	}
	
	private static void remove0(Path directory) throws IOException{
		if (!Files.exists(directory)) return;
		DirectoryStream<Path> stream = Files.newDirectoryStream(directory);
		for (Path p : stream){
			if (Files.isDirectory(p)){
				remove0(p);
				Files.deleteIfExists(p);
			}
			else{
				Files.delete(p);
			}
		}
		stream.close();
		Files.delete(directory);
	}
}
