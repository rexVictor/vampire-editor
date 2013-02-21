package vampire.editor.domain.config;

import java.awt.Font;
import java.awt.Image;
import java.util.Map;
import java.util.Set;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;

public class ResourcesHolder implements ResourcesHolderAPI{
	
	private final Map<String, Font> fonts;
	
	private final Map<String, Image> borders;
	
	private final Map<String, Image> lines;
	
	

	public ResourcesHolder(Map<String, Font> fonts, Map<String, Image> borders,
			Map<String, Image> lines) {
		super();
		this.fonts = fonts;
		this.borders = borders;
		this.lines = lines;
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
		return new Dictionary(null, "general");
	}
	
	public Image getBorder(String key){
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
			if (fonts.get(s).equals(font)) return s;
		}
		return null;
	}
	


}
