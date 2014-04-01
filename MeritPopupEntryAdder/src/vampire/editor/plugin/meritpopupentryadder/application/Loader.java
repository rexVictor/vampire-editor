package vampire.editor.plugin.meritpopupentryadder.application;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import vampire.editor.plugin.api.domain.DictionaryAPI;

public class Loader {
	
	private final Path fileDirectory = Paths.get("resources", "plugins", "MeritPopupEntryAdder"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	
	public static final Listener listener = new Listener();
	
	private final Comparator comparator;
	
	private final DictionaryAPI dictionary;
	
	
	public Loader(DictionaryAPI dictionary) {
		super();
		this.comparator = new Comparator(dictionary);
		this.dictionary = dictionary;
	}

	public SortedMap<Integer,SortedSet<String>> load(Path p){
		SortedMap<Integer, SortedSet<String>> costToNamesMap = new TreeMap<>();
		Properties properties = new Properties();
		try (InputStream stream = Files.newInputStream(p)){
			properties.load(stream);
			Set<Object> keys = properties.keySet();
			for (Object o : keys){
				String s = (String) o;
				String value = properties.getProperty(s);
				Set<Integer> ints = new HashSet<>();
				if (value.length() > 1){
					int start = Integer.parseInt(value.substring(0, 1));
					int end = Integer.parseInt(value.substring(2));
					for (int i = start; i <= end; i++){
						ints.add(i);
					}
				}
				else {
					ints.add(Integer.parseInt(value));
				}
				for (int cost : ints){
					SortedSet<String> names = costToNamesMap.get(cost);
					if (names == null){
						names = new TreeSet<>(comparator);
						costToNamesMap.put(cost, names);
					}
					names.add(s);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return costToNamesMap;
	}
	
	public Map<String, JPopupMenu> loadFiles(){
		Map<String, JPopupMenu> map = new HashMap<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(fileDirectory)){
			for (Path p: stream){
				if (!Files.isDirectory(p)){
					JPopupMenu popupMenu = new JPopupMenu();
					SortedMap<Integer, SortedSet<String>> entries = load(p);
					System.out.println(entries);
					Set<Integer> costs = entries.keySet();
					for (Integer i : costs){
						JMenu menu = new JMenu(i+""); //$NON-NLS-1$
						popupMenu.add(menu);
						Set<String> names = entries.get(i);
						for (String s : names){
							JMenuItem menuItem = new JMenuItem(dictionary.getValue(s));
							menuItem.addActionListener(listener);
							menu.add(menuItem);
						}
					}
					map.put(p.getFileName().toString(), popupMenu);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
