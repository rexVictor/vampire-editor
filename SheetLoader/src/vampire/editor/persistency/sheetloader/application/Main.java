package vampire.editor.persistency.sheetloader.application;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;

public class Main {
	
	public static void main(String[] args) throws JsonParseException, IOException{
		JsonParser parser;
		JsonFactory factory = new JsonFactory();
		Path path = Paths.get("SheetPersistencyProtoType/valueViews.json");
		System.out.println(path.toAbsolutePath());
		parser = factory.createJsonParser(path.toFile());
	    MinimalPrettyPrinter printer = new MinimalPrettyPrinter();
	    
		System.out.println(parser);
	}

}
