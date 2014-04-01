package vampire.editor.plugin.popupentryadder;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import vampire.editor.plugin.api.domain.DictionaryAPI;

public class Loader {
	
	private final Path fileDirectory = Paths.get("resources", "plugins", "traitpopupentryadder"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	
	private final DictionaryAPI dictionary;
	
	public static final Listener listener = new Listener();
	
	private final Comparator comparator;
	
	
	public Loader(DictionaryAPI dictionary) {
		super();
		this.dictionary = dictionary;
		this.comparator = new Comparator(dictionary);
	}

	public SortedSet<String> load(Path p){
		SortedSet<String> entries = new TreeSet<>(comparator);
		try (Scanner scanner = new Scanner(p)){
			while (scanner.hasNextLine()){
				entries.add(dictionary.getValue(scanner.nextLine()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Collections.sort(entries, Collator.getInstance());
		return entries;
	}
	
	public Map<String, JPopupMenu> loadFiles(){
		Map<String, JPopupMenu> map = new HashMap<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(fileDirectory)){
			for (Path p: stream){
				if (!Files.isDirectory(p)){
					JPopupMenu menu = new JPopupMenu();
					SortedSet<String> entries = load(p);
					for (String s : entries){
						JMenuItem menuItem = new JMenuItem(s);
						menuItem.addActionListener(listener);
						menu.add(menuItem);
					}
					map.put(p.getFileName().toString(), menu);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
