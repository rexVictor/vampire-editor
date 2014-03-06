package vampire.editor.plugin.metapopupentryadder;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Loader {
	
	private final Path fileDirectory = Paths.get("resources", "plugins", "metapopupentryadder");
	
	public List<String> load(Path p){
		List<String> entries = new LinkedList<>();
		try (Scanner scanner = new Scanner(p)){
			while (scanner.hasNextLine()){
				entries.add(scanner.nextLine());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return entries;
	}
	
	public Map<String, List<String>> loadFiles(){
		Map<String, List<String>> map = new HashMap<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(fileDirectory)){
			for (Path p: stream){
				if (!Files.isDirectory(p)){
					map.put(p.getFileName().toString(), load(p));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
