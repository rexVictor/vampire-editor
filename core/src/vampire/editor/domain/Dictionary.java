package vampire.editor.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import vampire.editor.plugin.api.domain.DictionaryAPI;

public class Dictionary implements DictionaryAPI {
	
	private final Map<String, String> valueToKey = new HashMap<>();
	
	private final ResourceBundle bundle;
	
	
	
	public Dictionary(ResourceBundle bundle) {
		super();
		this.bundle = bundle;
	}

	@Override
	public String getKey(String value){
		return null;
	}
	
	@Override
	public String getValue(String key){
		return null;
	}
	
	Set<String> getKeys(){
		return bundle.keySet();
	}
	
	
	
	

}
