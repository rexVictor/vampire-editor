package vampire.editor.sheetloader.application.exporter.mock;

import java.awt.Image;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.imageio.ImageIO;


import vampire.editor.plugin.api.domain.BorderAPI;
import vampire.editor.plugin.api.domain.DictionaryAPI;
import vampire.editor.plugin.api.domain.ResourcesHolderAPI;

public class ResourcesHolderMock implements ResourcesHolderAPI{
	
	public ResourcesHolderMock(){
	}

	@Override
	public DictionaryAPI getDictionary(String key) {
		return new DictionaryTestMock();
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
	public BorderAPI getBorder(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Path> getDefaultSheets() {
		// TODO Auto-generated method stub
		return null;
	}

}
