package vampire.editor.sheetloader.application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import vampire.editor.plugin.fullapi.sheet.IData;
import vampire.editor.plugin.fullapi.sheet.IMetaEntry;
import vampire.editor.plugin.fullapi.sheet.SheetConstructors;

public class MetaParser {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	private final SheetConstructors constructors;
	
	public MetaParser(SheetConstructors constructors){
		this.constructors = constructors;
		SimpleModule module = new SimpleModule();
		module.addAbstractTypeMapping(IMetaEntry.class, constructors.getImplementingClassOf(IMetaEntry.class));
		mapper.registerModule(module);
	}

	public IMetaEntry getMetaEntry(Object object){
		try {
			String string = mapper.writeValueAsString(object);
			IMetaEntry entry = mapper.readValue(string, IMetaEntry.class);
			return entry;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public IData<IMetaEntry> getMeta(Object object) throws	InstantiationException, IllegalAccessException{
		List<?> obj = mapper.convertValue(object, ArrayList.class);
		@SuppressWarnings("unchecked")
		IData<IMetaEntry> meta = constructors.getImplementingClassOf(IData.class).newInstance();
		meta.setName("metas");
		for (Object o : obj){
			meta.add(getMetaEntry(o));
		}
		return meta;				
	}
	
	
}
