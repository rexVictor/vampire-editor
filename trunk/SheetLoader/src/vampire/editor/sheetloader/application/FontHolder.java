package vampire.editor.sheetloader.application;

import java.awt.Font;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vampire.editor.plugin.api.plugin.ResourcesHolderAPI;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class FontHolder {
	
	private final Map<Integer, Font> fontMap = new HashMap<>();
	
	private final Path path;
	
	private final ResourcesHolderAPI holder;

	public FontHolder(Path path, ResourcesHolderAPI holder) throws JsonParseException, JsonMappingException, IOException {
		super();
		this.path = path;
		this.holder = holder;
		load();
	}
	
	private void load() throws JsonParseException, JsonMappingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
	
		@SuppressWarnings("unchecked")
		List<Map<String, ?>> fonts = mapper.readValue(path.toFile(), List.class);
		for (Map<String, ?> fontMap : fonts){
			int id = (Integer) fontMap.remove("id");
			String key = (String) fontMap.remove("key");
			int style = (Integer) fontMap.remove("style");
			int size = (Integer) fontMap.remove("size");
			Font font = holder.getFont(key);
			Font acutalFont = font.deriveFont((float) size).deriveFont(style);
			this.fontMap.put(id, acutalFont);
		}
		
	}
	
	@Override
	public String toString(){
		return fontMap.toString();
	}
	
	public Font getFontById(int id){
		return fontMap.get(id);
	}
	
	

}
