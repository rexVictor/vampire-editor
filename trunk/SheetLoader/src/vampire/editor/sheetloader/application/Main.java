package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import vampire.editor.domain.sheet.Category;
import vampire.editor.domain.sheet.Classes;
import vampire.editor.domain.sheet.Data;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleValueInstantiators;

public class Main {
	
	public static void main(String[] args) throws JsonParseException, IOException{
		ObjectMapper mapper = new ObjectMapper();
		Path path = Paths.get("SheetPersistencyProtoType", "default.json");
		Map<?, ?> doc = mapper.readValue(path.toFile(), Map.class);
		
		MetaParser parser = new MetaParser();
		
		parser.getMeta(doc.get("meta"));
		
		TraitsParser traitsParser = new TraitsParser(new Classes());
		Data<Category> data = traitsParser.getCategories(doc.get("traits"));
		System.out.println(data);
		
		
	}

}
