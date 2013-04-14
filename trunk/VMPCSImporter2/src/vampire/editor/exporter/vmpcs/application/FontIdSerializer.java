package vampire.editor.exporter.vmpcs.application;

import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class FontIdSerializer extends StdSerializer<Font>{
	
	private final Map<Font, Integer> fonts = new HashMap<>();
	
	private Integer i = 0;
	
	public FontIdSerializer(){
		super(Font.class);
	}

	@Override
	public void serialize(Font font, JsonGenerator gen, SerializerProvider provider)
			throws IOException, JsonGenerationException {
		if (!fonts.containsKey(font)){
			fonts.put(font, i++);
		}
		gen.writeNumber(fonts.get(font));
	}
	
	public Map<Font, Integer> getFonts(){
		return fonts;
	}
}
