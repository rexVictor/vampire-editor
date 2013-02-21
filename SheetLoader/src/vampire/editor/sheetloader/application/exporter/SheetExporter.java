package vampire.editor.sheetloader.application.exporter;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Path;
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
		protoFiles.put(Font.class, new ProtoExport());
		protoFiles.put(Value.class, new ProtoExport());
		protoFiles.put(MetaEntry.class, new ProtoExport(true));
		protoFiles.put(Trait.class, new ProtoExport(true));
		protoFiles.put(SubCategory.class, new ProtoExport(true));
		protoFiles.put(Category.class, new ProtoExport(true));
		
		fontSetter = new FontSetter(fonts);
		
		idCalculators.put(Font.class, fonts);
		idCalculators.put(Value.class, values);
		idCalculators.put(SubCategoryViewAttributes.class, new IdCalculator<>());
		idCalculators.put(ValueViewAttributes.class, new IdCalculator<>());
		idCalculators.put(TraitViewAttributes.class, new IdCalculator<>());
		idCalculators.put(CategoryViewAttributes.class, new IdCalculator<>());
		idCalculators.put(MetaEntryViewAttributes.class, new IdCalculator<>());
	}
	
	public SheetExporter(VampireDocument document, ResourcesHolderAPI resourcesHolder) {
		super();
		this.resourcesHolder = resourcesHolder;
		sheet = document.getSheet();
		modelToViewModelMapper = document.getModelToViewModelMapper();
	}
	
	public Map<?, ?> export(Path path) throws JsonGenerationException, JsonMappingException, IOException{
		Data<MetaEntry> metaEntries = sheet.getMeta();
		exportMetaEntries(metaEntries);
		sheetMap.put("meta", protoFiles.get(MetaEntry.class).getList());
		Data<Category> categories = sheet.getCategories();
		List<Map<String, Object>> categoryList = exportCategories(categories);
		sheetMap.put("traits", categoryList);
		
		makeFonts();
		
		
		serialize(path);
		return null;
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
	
	private void serialize(Path path) throws JsonGenerationException, JsonMappingException, IOException{
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		Set<Class<?>> classes = ClassToFileMapper.paths.keySet();
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
			if (object instanceof TraitViewAttributes){
				System.out.println();
			}
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
