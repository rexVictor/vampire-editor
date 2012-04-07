package vampire.editor.persistency.sheetloader.domain;


import java.util.HashMap;
import java.util.Map;

import vampire.editor.plugin.api.domain.sheet.view.ValueViewAttributes;


public class Attributes {
	
	private final Map<Integer, ProtoValueViewAttribute> viewAttributes = new HashMap<>();
	
	public void putValueViewAttribute(ProtoValueViewAttribute att){
		viewAttributes.put(att.hashCode(), att);
	}
	
	public ValueViewAttributes getValueViewAttributes(int id){
		return new ValueViewAttribute(viewAttributes.get(id));
	}
	
	
	
	
}
