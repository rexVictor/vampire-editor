package vampire.editor.tests;

import vampire.editor.plugin.api.domain.DictionaryAPI;

public class DictionaryTestImplementation implements DictionaryAPI {

	@Override
	public String getValue(String key) {
		return key;
	}

	@Override
	public String getKey(String value) {
		return value;
	}

}
