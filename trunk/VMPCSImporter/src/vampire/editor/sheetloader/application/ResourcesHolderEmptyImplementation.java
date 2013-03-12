package vampire.editor.sheetloader.application;

import java.awt.Font;
import java.awt.Image;
import java.nio.file.Path;
import java.util.Map;

import vampire.editor.plugin.api.domain.BorderAPI;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;

public class ResourcesHolderEmptyImplementation implements ResourcesHolderAPI{
	
	private final Font font = new Font(Font.SANS_SERIF, 0, 0); 

	@Override
	public Font getFont(String key) {
		return font;
	}

	@Override
	public DictionaryAPI getDictionary(String key) {
		return null;
	}

	@Override
	public Image getLine(String key) {
		return null;
	}

	@Override
	public String getKeyOfFont(Font font) {
		return "key";
	}

	@Override
	public BorderAPI getBorder(String key) {
		return null;
	}

	@Override
	public Map<String, Path> getDefaultSheets() {
		return null;
	}

}
