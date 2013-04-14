package vampire.editor.sheetloader.application.exporter.mock;

import vampire.editor.plugin.api.domain.DictionaryAPI;

public class DictionaryTestMock implements DictionaryAPI {

	@Override
	public String getValue(String key) {
		return key;
	}

	@Override
	public String getKey(String value) {
		return value;
	}

}
