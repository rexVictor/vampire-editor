package vampire.editor.domain;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.Test;

public class DictionaryTest {

	@Test
	public void testDictionary() {
		Dictionary dictionary = null;
	//TODO 	Dictionary dictionary = new Dictionary(null);
		Set<String> keySet = dictionary.getKeys();
		List<String> keys = new ArrayList<>(keySet);
		Random random = new Random();
		for (int i = 0; i<10; i++){
			int testNumber = random.nextInt(keys.size());
			String testKey = keys.get(testNumber);
			String testValue  = dictionary.getValue(testKey);
			String resultKey = dictionary.getKey(testValue);
			assertEquals(testKey, resultKey);
		}
	}

}
