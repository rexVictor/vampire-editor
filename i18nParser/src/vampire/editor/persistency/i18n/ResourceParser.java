package vampire.editor.persistency.i18n;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import vampire.editor.persistency.i18n.Dictionary;

public class ResourceParser {
	
	private final Path file;
	
	public ResourceParser(String path){
		file = Paths.get(path);
	}
	
	public void parse(Dictionary dictionary) throws IOException{
		
		try(Scanner scanner = new Scanner(file)){
			while (scanner.hasNextLine()){
				String line = scanner.nextLine();
				if (line.startsWith("//")) continue;
				int index = line.indexOf("=");
				String key = line.substring(0, index);				
				String value = line.substring(index+1);
				dictionary.addPair(key, value);
			}
		}
		
		
	}
	
	

}
