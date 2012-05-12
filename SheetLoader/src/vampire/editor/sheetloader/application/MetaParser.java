package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.module.SimpleModule;

import vampire.editor.domain.sheet.Data;
import vampire.editor.domain.sheet.MetaEntry;
import vampire.editor.plugin.fullapi.sheet.IData;
import vampire.editor.plugin.fullapi.sheet.IMetaEntry;

public class MetaParser {
	
	private final ObjectMapper mapper = new ObjectMapper();

	public IMetaEntry getMetaEntry(Object object) throws JsonGenerationException, JsonMappingException{
		try {
			String string = mapper.writeValueAsString(object);
			IMetaEntry entry = mapper.readValue(string, MetaEntry.class);
			return entry;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public IData<IMetaEntry> getMeta(Object object) throws JsonGenerationException, JsonMappingException{
		try {
			String string = mapper.writeValueAsString(object);
			List<?> obj = mapper.readValue(string, ArrayList.class);
			IData<IMetaEntry> meta = new Data<>(null);
			for (Object o : obj){
				meta.add(getMetaEntry(o));
			}
			return meta;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void test(){
		SimpleModule module = new SimpleModule();
		
		module.addValueInstantiator(null, new ValueInstantiator() {
			
			@Override
			public String getValueTypeDesc() {
				// TODO Auto-generated method stub
				return null;
			}
		});
				
	}
	
}
