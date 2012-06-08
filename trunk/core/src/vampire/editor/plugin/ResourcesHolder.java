package vampire.editor.plugin;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.plugin.ResourcesHolderAPI;

public class ResourcesHolder implements ResourcesHolderAPI{
	
	private final Map<String, Font> fonts = new HashMap<>();
	
	public ResourcesHolder(){
		//TODO das ist nur ein TestFall!
				Path path = Paths.get("resources", "CAS_ANTN.TTF");
				try {
					Font font = Font.createFont(Font.TRUETYPE_FONT, path.toFile());
					fonts.put("cas_antn", font);
				} catch (FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		// TODO Auto-generated method stub
		return null;
	}
	


}
