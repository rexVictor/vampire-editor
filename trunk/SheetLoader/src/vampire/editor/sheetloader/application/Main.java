package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import vampire.editor.domain.sheet.Category;
import vampire.editor.domain.sheet.Data;
import vampire.editor.plugin.api.domain.sheet.CategoryAPI;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Main {
	
	public static void main(String[] args) throws JsonParseException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		Path path = Paths.get("SheetPersistencyProtoType", "default.json");
		Map<?, ?> doc = mapper.readValue(path.toFile(), Map.class);
		
		MetaParser parser = new MetaParser();
		
		parser.getMeta(doc.get("meta"));
		
		TraitsParser traitsParser = new TraitsParser();
		Data<CategoryAPI, Category> data = traitsParser.getCategories(doc.get("traits"));
		System.out.println(data);
	}

}
