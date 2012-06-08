package vampire.editor.sheetloader.application.exporter;

import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class FontSerializer extends StdSerializer<Font>{
	
	

	protected FontSerializer() {
		super(Font.class);		
	}

	@Override
	public void serialize(Font value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		Map<String, Object> map = new HashMap<>();
		map.put("name", value.getName());
		map.put("style", value.getStyle());
		map.put("size", value.getSize());
		jgen.writeObject(map);
		
	}
	
}