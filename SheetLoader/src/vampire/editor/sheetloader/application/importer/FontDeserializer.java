package vampire.editor.sheetloader.application.importer;

import java.awt.Font;
import java.io.IOException;
import java.util.Map;

import vampire.editor.plugin.api.domain.ResourcesHolderAPI;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class FontDeserializer extends StdDeserializer<Font>{
	
	private final ResourcesHolderAPI resources;
	
	public FontDeserializer(ResourcesHolderAPI resources){
		super(Font.class);
		this.resources = resources;
	}

	@Override
	public Font deserialize(JsonParser arg0, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		@SuppressWarnings("unchecked")
		Map<String, Object> map = arg0.readValueAs(Map.class);
		String key = (String) map.get("key");
		int style = (int) map.get("style");
		int size = (int) map.get("size");
		Font font = resources.getFont(key);
		font = font.deriveFont(style, size);
		return font;
	}

}
