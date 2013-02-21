package vampire.editor.sheetloader.application.exporter;

import java.awt.Font;
import java.io.IOException;
import java.util.HashMap;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class FontEmptySerializer extends StdSerializer<Font>{

	protected FontEmptySerializer() {
		super(Font.class);
	}

	@Override
	public void serialize(Font arg0, JsonGenerator arg1, SerializerProvider arg2)
			throws IOException, JsonGenerationException {
		arg1.writeObject(new HashMap<>());		
	}

}
