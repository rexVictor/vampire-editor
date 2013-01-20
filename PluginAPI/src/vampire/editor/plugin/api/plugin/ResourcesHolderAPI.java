package vampire.editor.plugin.api.plugin;

import java.awt.Font;
import java.awt.Image;

public interface ResourcesHolderAPI {
	
	public Font getFont(String key);
	
	public DictionaryAPI getDictionary(String key);
	
	public Image getLine(String key);
		
	

}