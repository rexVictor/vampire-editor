package vampire.editor.exporter.vmpcs.application;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import vampire.editor.fileformat.vmpcs.domain.FileNames;
import vampire.editor.fileformat.vmpcs.domain.IntegerWrap;
import vampire.editor.fileformat.vmpcs.domain.MapidResolver;
import vampire.editor.fileformat.vmpcs.domain.ProtoSheet;
import vampire.editor.fileformat.vmpcs.domain.StringConstants;
import vampire.editor.plugin.api.domain.sheet.Sheet;
import vampire.editor.plugin.api.domain.sheet.Value;

public class SheetExporter {
	
	private final MapidResolver resolver = new MapidResolver();
	
	private final ObjectMapper objectMapper = new ObjectMapper();
	
	@SuppressWarnings("resource")
	public Map<Integer, Object> exportSheet(Sheet sheet, Path path) 
			throws JsonGenerationException, JsonMappingException, IOException{
		IntegerWrap integerWrap = new IntegerWrap();
		ProtoSheet protoSheet = new ProtoSheet(sheet, integerWrap);
		Map<Integer, Object> map = resolver.generateMapIdMap(protoSheet);
		Map<Value, Integer> values = ProtoValueBuilder.buildProtoValues(map, integerWrap);
		serializeValues(values, path);
		OutputStream stream = Files.newOutputStream(path.resolve(FileNames.SHEET));
		objectMapper.writeValue(stream, protoSheet);
		return map;
	}
	
	private void serializeValues(Map<Value, Integer> valueMap, Path target) 
			throws JsonGenerationException, JsonMappingException, IOException{
		List<Map<String, Object>> values = new LinkedList<>();
		Set<Value> valueSet = valueMap.keySet();
		for (Value value : valueSet){
			Map<String, Object> map = objectMapper.convertValue(value,
					new TypeReference<Map<String, Object>>(){});
			map.put(StringConstants.ID, valueMap.get(value));
			values.add(map);
		}
		@SuppressWarnings("resource")
		OutputStream outputStream = Files.newOutputStream(target.resolve(FileNames.VALUES));
		objectMapper.writeValue(outputStream, values);
		
	}
	
}
