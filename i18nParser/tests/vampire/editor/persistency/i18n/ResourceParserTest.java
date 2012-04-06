package vampire.editor.persistency.i18n;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import vampire.editor.persistency.i18n.Dictionary;

public class ResourceParserTest {
	
	private class TestDictionary implements Dictionary{
		
		private final Map<String, String> keyToValue = new HashMap<>();
		
		private final Map<String, String> valueToKey = new HashMap<>();

		@Override
		public String getValue(String key) {
			return keyToValue.get(key);
		}

		@Override
		public String getKey(String value) {
			return valueToKey.get(value);
		}

		@Override
		public void addPair(String key, String value) {
			keyToValue.put(key, value);
			valueToKey.put(value, key);
		
		}
		
		@Override
		public String toString(){
			return keyToValue.toString();
		}
		
		public Set<String> getKeySet(){
			return keyToValue.keySet();
		}
	}

	
	@Test
	public void testParser() throws IOException {
		ResourceParser parser = new ResourceParser("testCase.properties");
		TestDictionary dictionary = new TestDictionary();
		parser.parse(dictionary);
		Set<String> keys = dictionary.getKeySet();
		for (String k : keys){
			assertEquals(k, dictionary.getKey(dictionary.getValue(k)));
		}
		
		
	}

}
