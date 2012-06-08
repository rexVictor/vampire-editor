package vampire.editor.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import vampire.editor.plugin.api.domain.DictionaryAPI;

public class Dictionary implements DictionaryAPI {
	
	private final Map<String, String> valueToKey = new HashMap<>();
	
	private final Map<String, String> keyToValue = new HashMap<>();
	
	private final String name;
	
	public Dictionary(ResourceBundle bundle, String name) {
		super();
		this.name = name;
		initialize(bundle);
	}
	
	private void initialize(ResourceBundle bundle){
		Set<String> keys = bundle.keySet();
		for (String key : keys){
			String value = bundle.getString(key);
			keyToValue.put(key, value);
			valueToKey.put(value, key);
		}
		
	}	
	

	@Override
	public String getKey(String value){
		return valueToKey.get(value);
	}
	
	@Override
	public String getValue(String key){
		return keyToValue.get(key);
	}

	public String getName() {
		return name;
	}
	
	
	
	
	
	
	
	

}
