package vampire.editor.domain.config;

import java.awt.Font;
import java.awt.Image;
import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import vampire.editor.domain.Border;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;

public class ResourcesHolder implements ResourcesHolderAPI{
	
	private final Map<String, Font> fonts;
	
	private final Map<String, Border> borders;
	
	private final Map<String, Image> lines;
	
	private final Map<String, Dictionary> dictionaries;
	
	private final Map<String, Path> defaultSheets;
	
	

	public ResourcesHolder(Map<String, Font> fonts, Map<String, Border> borders,
			Map<String, Image> lines, Map<String, Dictionary> dictionaries,
			Map<String, Path> defaultSheets) {
		super();
		this.fonts = fonts;
		this.borders = borders;
		this.lines = lines;
		this.dictionaries = dictionaries;
		this.defaultSheets = defaultSheets;
	}

	@Override
	public Font getFont(String key){
		Font toReturn = fonts.get(key);
		if (toReturn == null)
			toReturn = new Font(key, 0, 10);
		return toReturn;
	}

	@Override
	public DictionaryAPI getDictionary(String key) {
		DictionaryAPI dictionary = dictionaries.get(key);
		if (dictionary == null){
			return new Dictionary(null, "");
		}
		return dictionary;
	}
	
	public Border getBorder(String key){
		return borders.get(key);
	}
	
	@Override
	public Image getLine(String key){
		return lines.get(key);
	}

	@Override
	public String getKeyOfFont(Font font) {
		Set<String> keys = fonts.keySet();
		for (String s : keys){
			Font current = fonts.get(s);
			Font toCompare = font.deriveFont((float) current.getSize()).deriveFont(current.getStyle());
			if (current.equals(toCompare)) return s;
		}
		return null;
	}
	
	public Path getDefaultSheet(String key){
		return defaultSheets.get(key);
	}
	
	public Map<String, Path> getDefaultSheets(){
		return Collections.unmodifiableMap(defaultSheets);
	}
	


}
