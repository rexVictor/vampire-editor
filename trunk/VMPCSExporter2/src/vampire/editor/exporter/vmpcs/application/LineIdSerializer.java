package vampire.editor.exporter.vmpcs.application;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import vampire.editor.plugin.api.domain.sheet.view.LineAttributesAPI;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class LineIdSerializer extends StdSerializer<LineAttributesAPI>{
	
private final Map<LineAttributesAPI, Integer> lineAtts = new HashMap<>();
	
	private Integer i = 0;
	
	public LineIdSerializer(){
		super(LineAttributesAPI.class);
	}

	@Override
	public void serialize(LineAttributesAPI font, JsonGenerator gen, SerializerProvider provider)
			throws IOException, JsonGenerationException {
		if (!lineAtts.containsKey(font)){
			lineAtts.put(font, i++);
		}
		gen.writeNumber(lineAtts.get(font));
	}
	
	public Map<LineAttributesAPI, Integer> getLines(){
		return lineAtts;
	}

}
