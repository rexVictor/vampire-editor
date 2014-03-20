package vampire.editor.plugin.meritpopupentryadder.persistency;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.meritpopupentryadder.application.Comparator;

public class Loader {
	
private final Path fileDirectory = Paths.get("resources", "plugins", "MeritPopupEntryAdder");
	
	private final Comparator comparator;
	
	public Loader(DictionaryAPI dictionary){
		comparator = new Comparator(dictionary);
	}
	
	private void addToMap(Map<Integer, List<String>> map, int cost, String entry){
		List<String> entries = map.get(cost);
		if (entries == null){
			entries = new LinkedList<>();
			map.put(cost, entries);
		}
		entries.add(entry);
	}
	
	public Map<Integer, List<String>> load(Path p){
		Map<Integer, List<String>> map = new TreeMap<Integer, List<String>>();
		Properties properties = new Properties();
		try (InputStream stream = Files.newInputStream(p)){
			properties.load(stream);
			Set<Object> keys = properties.keySet();
			for (Object o : keys){
				String value = (String) properties.get(o);
				if (value.length() == 1){
					int cost = Integer.parseInt(value);
					addToMap(map, cost, (String) o);
				}
				else {
					int start = Integer.parseInt(value.substring(0, 1));
					int end = Integer.parseInt(value.substring(value.length()-1));
					for (int i = start; i <= end; i ++){
						addToMap(map, i, (String) o);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		Set<Integer> costs = map.keySet();
		for (int i : costs){
			Collections.sort(map.get(i), comparator);
		}
		return map;
	}
	
	public Map<String, Map<Integer, List<String>>> loadFiles(){
		Map<String, Map<Integer, List<String>>> map = new HashMap<>();
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
