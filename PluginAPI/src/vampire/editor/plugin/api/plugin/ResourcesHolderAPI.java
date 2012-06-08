package vampire.editor.plugin.api.plugin;

import java.awt.Font;

import vampire.editor.plugin.api.domain.DictionaryAPI;

public interface ResourcesHolderAPI {
	
	public Font getFont(String key);
	
	public DictionaryAPI getDictionary(String key);
		
	

}
