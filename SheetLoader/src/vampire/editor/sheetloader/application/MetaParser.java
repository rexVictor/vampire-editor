package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import vampire.editor.domain.sheet.Data;
import vampire.editor.domain.sheet.MetaEntry;

public class MetaParser {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	public MetaParser(){
	}

	public MetaEntry getMetaEntry(Object object){
		try {
			String string = mapper.writeValueAsString(object);
			MetaEntry entry = mapper.readValue(string, MetaEntry.class);
			return entry;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Data<MetaEntry> getMeta(Object object) throws	InstantiationException, IllegalAccessException{
		List<?> obj = mapper.convertValue(object, ArrayList.class);
		@SuppressWarnings("unchecked")
		Data<MetaEntry> meta = Data.class.newInstance();
		meta.setName("metas");
		for (Object o : obj){
			meta.add(getMetaEntry(o));
		}
		return meta;				
	}
	
	
}
