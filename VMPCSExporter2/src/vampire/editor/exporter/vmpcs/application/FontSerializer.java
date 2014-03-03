package vampire.editor.exporter.vmpcs.application;

import java.awt.Font;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class FontSerializer extends StdSerializer<Font>{
	
	public FontSerializer(){
		super(Font.class);
	}

	@Override
	public void serialize(Font font, JsonGenerator gen, SerializerProvider arg2)
			throws IOException, JsonGenerationException {
		gen.writeStartObject();
		gen.writeStringField("key", font.getName());
		gen.writeNumberField("size", font.getSize());
		gen.writeNumberField("style", font.getStyle());
		gen.writeEndObject();
	}

}
