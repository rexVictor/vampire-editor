package vampire.editor.sheetloader.application.exporter;

import java.awt.Font;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import vampire.editor.domain.sheet.*;
import vampire.editor.domain.sheet.view.*;

import vampire.editor.plugin.api.domain.ResourcesHolderAPI;
import vampire.editor.sheetloader.common.ClassToFileMapper;
import vampire.editor.sheetloader.common.ModelToViewMap;

public class SheetExporter {
	
	private static final Path temp = Paths.get(System.getProperty("java.io.tmpdir"), "vampireeditor", "vmpcsexporter");
	
	private static final int id = 0;
	
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
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private final Map<Class<?>, ProtoExport> protoFiles = new HashMap<>();
	
	private final Map<Class<?>, IdCalculator<?>> idCalculators = new HashMap<>();
	
	private final IdCalculator<Font> fonts;
	
	private final IdCalculator<Value> values;
	
	private final ModelToViewModelMapper modelToViewModelMapper;
	
	private final ModelToViewMap modelToViewMap = new ModelToViewMap();
	
	private final RawExporter rawExporter = new RawExporter();
	
	private final FontSetter fontSetter;
	
	private final Map<String, Object> sheetMap = new HashMap<>();
	
	private final Sheet sheet;
	
	private final ResourcesHolderAPI resourcesHolder;
	
	private int valueMapId = 0;

	
	{
		SimpleModule module = new SimpleModule();
		FontEmptySerializer serializer = new FontEmptySerializer();
		module.addSerializer(serializer);
		mapper.registerModule(module);
		
		values = new IdCalculator<>();
		fonts = new IdCalculator<>();
		
		protoFiles.put(ValueViewAttributes.class, new ProtoExport());
		protoFiles.put(TraitViewAttributes.class, new ProtoExport());
		protoFiles.put(SubCategoryViewAttributes.class, new ProtoExport());
		protoFiles.put(CategoryViewAttributes.class, new ProtoExport());
		protoFiles.put(MetaEntryViewAttributes.class, new ProtoExport());
		protoFiles.put(HealthEntryViewAttributes.class, new ProtoExport());
		protoFiles.put(HealthViewAttributes.class, new ProtoExport());
		protoFiles.put(MeritEntryViewAttibutes.class, new ProtoExport());
		protoFiles.put(MeritViewAttributes.class, new ProtoExport());
		protoFiles.put(BloodPoolViewAttributes.class, new ProtoExport());
		protoFiles.put(Font.class, new ProtoExport());
		protoFiles.put(Value.class, new ProtoExport());
		
		protoFiles.put(MetaEntry.class, new ProtoExport(true));
		protoFiles.put(Trait.class, new ProtoExport(true));
		protoFiles.put(SubCategory.class, new ProtoExport(true));
		protoFiles.put(Category.class, new ProtoExport(true));
		protoFiles.put(HealthEntry.class, new ProtoExport(true));
		protoFiles.put(Merit.class, new ProtoExport(true));
		protoFiles.put(Merits.class, new ProtoExport(true));
		protoFiles.put(Health.class, new ProtoExport(true));
		protoFiles.put(BloodPool.class, new ProtoExport(true));
		
		fontSetter = new FontSetter(fonts);
		
		idCalculators.put(Font.class, fonts);
		idCalculators.put(Value.class, values);
		idCalculators.put(SubCategoryViewAttributes.class, new IdCalculator<>());
		idCalculators.put(ValueViewAttributes.class, new IdCalculator<>());
		idCalculators.put(TraitViewAttributes.class, new IdCalculator<>());
		idCalculators.put(CategoryViewAttributes.class, new IdCalculator<>());
		idCalculators.put(MetaEntryViewAttributes.class, new IdCalculator<>());
		idCalculators.put(HealthViewAttributes.class, new IdCalculator<>());
		idCalculators.put(HealthEntryViewAttributes.class, new IdCalculator<>());
		idCalculators.put(MeritEntryViewAttibutes.class, new IdCalculator<>());
		idCalculators.put(MeritViewAttributes.class, new IdCalculator<>());
		idCalculators.put(BloodPoolViewAttributes.class, new IdCalculator<>());
	}
	
	public SheetExporter(VampireDocument document, ResourcesHolderAPI resourcesHolder) {
		super();
		this.resourcesHolder = resourcesHolder;
		sheet = document.getSheet();
		modelToViewModelMapper = document.getModelToViewModelMapper();
	}
	
	public void export(Path path) throws JsonGenerationException, JsonMappingException, IOException{
		Data<MetaEntry> metaEntries = sheet.getMeta();
		exportMetaEntries(metaEntries);
		sheetMap.put("meta", protoFiles.get(MetaEntry.class).getList());
		Data<Category> categories = sheet.getCategories();
		List<Map<String, Object>> categoryList = exportCategories(categories);
		sheetMap.put("traits", categoryList);
		
		Merits merits = sheet.getMerits();
		Map<String, Object> meritMap = exportMerits(merits);
		sheetMap.put("merits", meritMap);
		
		Merits flaws = sheet.getFlaws();
		Map<String, Object> flawMap = exportMerits(flaws);
		sheetMap.put("flaws", flawMap);
		
		BloodPool bloodPool = sheet.getBloodPool();
		Map<String, Object> bloodPoolMap = exportBloodPool(bloodPool);
		bloodPoolMap.put("mapid", 0);
		sheetMap.put("bloodpool", bloodPoolMap);
		
		Health health = sheet.getHealth();
		Map<String, Object> healthMap = exportHealth(health);
		healthMap.put("mapid", 0);
		sheetMap.put("health", healthMap);
		
		sheetMap.put("border", sheet.getBorderKey());
		
		makeFonts();
		serialize(path);
		
	}
	
	private Map<String, Object> exportHealth(Health health){
		Map<String, Object> map = export(health);
		List<Map<String, Object>> entries = new LinkedList<>();
		for (HealthEntry entry : health){
			Map<String, Object> entryMap = exportHealthEntry(entry);
			entries.add(entryMap);
		}
		map.put("levels", entries);
		return map;
	}
	
	private Map<String, Object> exportHealthEntry(HealthEntry entry){
		Map<String, Object> map = export(entry);
		return map;
	}
	
	private Map<String, Object> exportMerits(Merits merits){
		Map<String, Object> map = export(merits);
		List<Map<String, Object>> meritEntries = new LinkedList<>();
		for (Merit merit : merits){
			Map<String, Object> entry = export(merit);
			meritEntries.add(entry);
		}
		map.put("entries", meritEntries);
		return map;
	}
	
	private Map<String, Object> exportBloodPool(BloodPool bloodPool){
		return export(bloodPool);
	}
	
	private void makeFonts(){
		Set<Font> fonts = this.fonts.getRegisteredObjects();
		ProtoExport protoFont = protoFiles.get(Font.class);
		for (Font font: fonts){
			Map<String, Object> map = new HashMap<>();
			String key = resourcesHolder.getKeyOfFont(font);
			map.put("id", this.fonts.getId(font));
			map.put("key", key);
			map.put("size", font.getSize());
			map.put("style", font.getStyle());
			protoFont.add(map);
		}
	}
	
	private void serialize(Path target){
		Path path = temp.resolve(id+"");
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		Set<Class<?>> classes = ClassToFileMapper.paths.keySet();
		try{
			Files.createDirectories(path);
			for (Class<?> clazz : classes){
				if (clazz == Sheet.class) continue;
				ProtoExport export = protoFiles.get(clazz);
				Path current = path.resolve(ClassToFileMapper.paths.get(clazz));
				mapper.writeValue(current.toFile(), export.getList());
			}
			Map<?, ?> map = modelToViewMap.toMap();
			Path mapPath = path.resolve("modeltoviewmap.json");
			mapper.writeValue(mapPath.toFile(), map);
			
			Path sheet = path.resolve("sheet.json");
			mapper.writeValue(sheet.toFile(), sheetMap);
			
			zip(target);
		}
		catch(IOException | URISyntaxException e){
			throw new VMPCSExportException(e);
		}
		finally{
			try {
				remove();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	private void remove() throws IOException{
		Path path = temp.resolve(id+"");
		remove0(path);
	}
	
	private void zip(Path path) throws IOException, URISyntaxException{
		Path current = temp.resolve(id+"");
		Map<String, String> env = new HashMap<String, String>();
		if (Files.exists(path)){
			Files.delete(path);
		}
	    env.put("create", "true");
	    URI fileUri = path.toUri();
	    URI zipUri = new URI("jar:" + fileUri.getScheme(), fileUri.getPath(), null);
	    System.out.println(zipUri);
	    FileSystem fs = FileSystems.newFileSystem(zipUri, env);
	    Path root = fs.getPath("/");
		DirectoryStream<Path> stream = Files.newDirectoryStream(current);
		for (Path p : stream){
			Path target = root.resolve(p.getFileName().toString());
			Files.copy(p, target);
		}
		stream.close();
		fs.close();
	}
	
	private List<Map<String, Object>> exportCategories(Data<Category> categories){
		List<Map<String, Object>> categoryList = new LinkedList<>();
		for (Category category : categories) {
			Map<String, Object> map = export(category);
			List<Map<String, Object>> subCategories = exportSubCategories(category);
			map.put("subCats", subCategories);
			categoryList.add(map);
		}
		return categoryList;
	}
	
	private List<Map<String, Object>> exportSubCategories(Category category){
		List<Map<String, Object>> subCategories = new LinkedList<>();
		for (SubCategory subCategory : category){
			Map<String, Object> map = export(subCategory);
			List<Map<String, Object>> traits = exportTraits(subCategory);
			map.put("traits", traits);
			subCategories.add(map);
		}
		return subCategories;
	}
	
	private List<Map<String, Object>> exportTraits(SubCategory subCategory){
		List<Map<String, Object>> traits = new LinkedList<>();
		for (Trait trait : subCategory){
			Map<String, Object> map = export(trait);
			Value value = trait.getValue();
			export(value);
			int id = values.getId(value);
			Map<String, Object> valueMap = new HashMap<>();
			valueMap.put("id", id);
			valueMap.put("mapid", valueMapId);
			map.put("value", valueMap);
			traits.add(map);
			valueMapId++;
		}
		return traits;
	}
	
	private void exportMetaEntries(Data<MetaEntry> metaEntries){
		for (MetaEntry entry : metaEntries) {
			export(entry);
		}
	}
	
	private <V, W> Map<String, Object> export(V object){
		Map<String, Object> map = rawExporter.export(object);
		@SuppressWarnings("unchecked")
		IdCalculator<V> calculator = (IdCalculator<V>) idCalculators.get(object.getClass());
		if (calculator != null){
			int id = calculator.getId(object);
			map.put("id", id);
		}
		fontSetter.addFont(object, map);
		ProtoExport protoExport = protoFiles.get(object.getClass());
		int mapid = protoExport.add(map);
		@SuppressWarnings("unchecked")
		W viewAtts = (W) modelToViewModelMapper.get(object);
		if (viewAtts instanceof ValueViewAttributes){
			export(viewAtts);
			@SuppressWarnings("unchecked")
			IdCalculator<W> viewAttCalculator = (IdCalculator<W>) idCalculators.get(viewAtts.getClass());
			modelToViewMap.putPair(object.getClass(), valueMapId, viewAttCalculator.getId(viewAtts));
		}
		if (viewAtts != null){
			export(viewAtts);
			@SuppressWarnings("unchecked")
			IdCalculator<W> viewAttCalculator = (IdCalculator<W>) idCalculators.get(viewAtts.getClass());
			modelToViewMap.putPair(object.getClass(), mapid, viewAttCalculator.getId(viewAtts));
		}
		return map;
	}
}
