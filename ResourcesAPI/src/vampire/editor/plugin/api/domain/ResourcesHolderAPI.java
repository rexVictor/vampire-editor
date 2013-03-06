package vampire.editor.plugin.api.domain;

import java.awt.Font;
import java.awt.Image;
import java.nio.file.Path;
import java.util.Map;


public interface ResourcesHolderAPI {
	
	public Font getFont(String key);
	
	public DictionaryAPI getDictionary(String key);
	
	public Image getLine(String key);
	
	public String getKeyOfFont(Font font);
	
	public BorderAPI getBorder(String key);
	
	public Map<String, Path> getDefaultSheets();
	
}
