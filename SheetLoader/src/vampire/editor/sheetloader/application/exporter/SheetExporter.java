package vampire.editor.sheetloader.application.exporter;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import vampire.editor.domain.sheet.Category;
import vampire.editor.domain.sheet.Data;
import vampire.editor.domain.sheet.Sheet;
import vampire.editor.domain.sheet.SubCategory;
import vampire.editor.domain.sheet.Trait;
import vampire.editor.plugin.fullapi.sheet.view.ICategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.IDataViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.ISubCategoryViewAttributes;
import vampire.editor.plugin.fullapi.sheet.view.IValueViewAttributes;

public class SheetExporter {
	
	private IdCalculator<Font> fonts = new IdCalculator<>();
	
	private IdCalculator<IValueViewAttributes> valueViewAttributes = new IdCalculator<>();
	
	private IdCalculator<ICategoryViewAttributes> categoryViewAttributes = new IdCalculator<>();
	
	private IdCalculator<ISubCategoryViewAttributes> subCategoryViewAttributes = new IdCalculator<>();
	
	public SheetExporter(){
		
	}
	
	public void export(Sheet sheet, Path path) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		SimpleModule module = new SimpleModule();
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		module.addSerializer(new FontSerializer());
		mapper.registerModule(module);
		mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		mapper.writeValue(path.toFile(), sheet);
		System.out.println(mapper.convertValue(sheet, Map.class));
	}
	
	private List<Map<String,Object>> exportCategories(Data<Category> categories){
		List<Map<String, Object>> toReturn = new LinkedList<>();
		for (Category category : categories){
			toReturn.add(exportCategory(category));
		}
		return toReturn;
	}
	
	private Map<String, Object> exportCategory(Category category){
		Map<String, Object> toReturn = new HashMap<>();
		
		toReturn.put("name", category.getName());
		
		ICategoryViewAttributes viewAtts = category.getViewAtts();
		int viewAttId = categoryViewAttributes.getId(viewAtts);
		toReturn.put("viewAtts", viewAttId);
		
		for (SubCategory subCategory : category){
			
		}
		
		return null;
	}
	
	private Map<String, Object> exportSubCategory(SubCategory subCategory){
		Map<String, Object> toReturn = new HashMap<>();
		
		toReturn.put("name", subCategory.getName());
		
		ISubCategoryViewAttributes viewAtts = subCategory.getViewAtts();
		int viewAttId = subCategoryViewAttributes.getId(viewAtts);
		toReturn.put("viewAtts", viewAttId);
		
		for (Trait trait : subCategory){
			
		}
		
		return null;
	}
	
	private int getDataViewAttId(IDataViewAttributes atts){
		return 0;
	}
	

	
	

}
