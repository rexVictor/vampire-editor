package vampire.editor.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import vampire.editor.plugin.api.plugin.DictionaryAPI;



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
		if (bundle == null) return;
		Set<String> keys = bundle.keySet();
		for (String key : keys){
			String value = bundle.getString(key);
			keyToValue.put(key, value);
			valueToKey.put(value, key);
		}
		
	}	
	

	@Override
	public String getKey(String value){
		String toReturn = valueToKey.get(value);
		if (toReturn == null)
			return value;
		return toReturn;
	}
	
	@Override
	public String getValue(String key){
		String toReturn = keyToValue.get(key);
		if (toReturn == null)
			return key;
		return toReturn;
	}

	public String getName() {
		return name;
	}
	
	
	
	
	
	
	
	

}
