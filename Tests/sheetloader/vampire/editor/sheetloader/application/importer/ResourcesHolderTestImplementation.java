package vampire.editor.sheetloader.application.importer;

import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;


import vampire.editor.gui.swing.view.DictionaryTestImplementation;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;

public class ResourcesHolderTestImplementation implements ResourcesHolderAPI{
	
	private final Map<String, Font> fonts = new HashMap<>();
	
	public ResourcesHolderTestImplementation(){
		try {
			fonts.put("cas_antn", Font.createFont(0, Paths.get("resources", "CAS_ANTN.TTF").toFile()));
		} catch (Throwable e) {
		
			throw new RuntimeException(e);
		}
	}

	@Override
	public Font getFont(String key) {
		Font font = fonts.get(key);
		if (font == null){
			font = new Font(key,0,0);
		}
		
		return font;
	}

	@Override
	public DictionaryAPI getDictionary(String key) {
		return new DictionaryTestImplementation();
	}

	@Override
	public Image getLine(String key) {
		try {
			return ImageIO.read(Paths.get("lines","default.gif").toFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getKeyOfFont(Font font) {
		return "cas_antn";
	}

}
